package test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientJMS extends Application {
	private Session session;
	private MessageProducer messageProducer;
	private MessageConsumer messageConsumer;
	private ObservableList<String> observableList;
	
	public static void main(String[] args){
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) {
		initialisation();
		
		primaryStage.setTitle("Client JMS");
		VBox vBox = new VBox();
		GridPane gridPane = new GridPane();
		gridPane.setVgap(10);
		gridPane.setHgap(10);
		gridPane.setPadding(new Insets(10));
		Label labelNom = new Label("Nom :");
		TextField textFieldNom = new TextField();
		Label labelPrenom = new Label("Prénom :");
		TextField textFieldPrenom = new TextField();
		Button buttonInscrire = new Button("Inscrire");
		gridPane.add(labelNom, 0, 0);
		gridPane.add(textFieldNom, 1, 0);
		gridPane.add(labelPrenom, 0, 1);
		gridPane.add(textFieldPrenom, 1, 1);
		gridPane.add(buttonInscrire, 0, 2);
		vBox.getChildren().add(gridPane);
		vBox.setPadding(new Insets(10));
		vBox.setSpacing(10);
		observableList = FXCollections.observableArrayList();
		ListView<String> listView = new ListView<String>(observableList);
		vBox.getChildren().add(listView);
		Scene scene = new Scene(vBox, 400, 300);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		buttonInscrire.setOnAction(e->{			
			try {
				String nom = textFieldNom.getText();
				String prenom = textFieldPrenom.getText();
				
				TextMessage textMessage = session.createTextMessage();
				textMessage.setText(nom+"_"+prenom);
				messageProducer.send(textMessage);
			} catch (JMSException e1) {
				e1.printStackTrace();
			}
		});
	}
	
	public void initialisation() {
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
			Connection connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			connection.start();
			
			Destination destination = session.createQueue("scolarite.queue");
			messageProducer = session.createProducer(destination);
			
			Destination destination2 = session.createQueue("eco.rep");
			messageConsumer = session.createConsumer(destination2);
			
			messageConsumer.setMessageListener(new MessageListener() {
				
				@Override
				public void onMessage(Message message) {
					if(message instanceof TextMessage) {
						try {
							String contenu = ((TextMessage) message).getText();
							observableList.add(contenu);
						} catch (JMSException e) {
							e.printStackTrace();
						}
					}
				}
			});
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
