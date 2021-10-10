import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Scenes {

    public static void mainScene(Stage stage) throws FileNotFoundException {

        HashMap<Predictor, PriorityQueue<Prediction>> map = Driver.readFile();

    Pane pane = new Pane();
    pane.setPrefHeight(598);
    pane.setPrefWidth(1070);

    Label label = new Label("Auto Completion Form");
    label.setLayoutX(301);
    label.setLayoutY(14);
    label.setPrefHeight(122);
    label.setPrefWidth(670);
    label.setFont(new Font("MV Boli",45));

    TextArea tf = new TextArea();
    tf.setLayoutX(301);
    tf.setLayoutY(183);
    tf.setPrefHeight(343);
    tf.setPrefWidth(502);

    Button bt[] = new Button[6];


    bt[0] = new Button();
    bt[0].setLayoutX(59);
    bt[0].setLayoutY(225);
    bt[0].setMnemonicParsing(false);
    bt[0].setPrefHeight(51);
    bt[0].setPrefWidth(175);
    bt[0].setVisible(false);

     bt[1] = new Button();
    bt[1].setLayoutX(59);
    bt[1].setLayoutY(318);
    bt[1].setMnemonicParsing(false);
    bt[1].setPrefHeight(51);
    bt[1].setPrefWidth(175);
        bt[1].setVisible(false);

    bt[2] = new Button();
    bt[2].setLayoutX(59);
    bt[2].setLayoutY(416);
    bt[2].setMnemonicParsing(false);
    bt[2].setPrefHeight(51);
    bt[2].setPrefWidth(175);
        bt[2].setVisible(false);

    bt[3] = new Button();
    bt[3].setLayoutX(855);
    bt[3].setLayoutY(225);
    bt[3].setMnemonicParsing(false);
    bt[3].setPrefHeight(51);
    bt[3].setPrefWidth(175);
        bt[3].setVisible(false);

    bt[4] = new Button();
    bt[4].setLayoutX(855);
    bt[4].setLayoutY(329);
    bt[4].setMnemonicParsing(false);
    bt[4].setPrefHeight(51);
    bt[4].setPrefWidth(175);
        bt[4].setVisible(false);

    bt[5] = new Button();
    bt[5].setLayoutX(855);
    bt[5].setLayoutY(428);
    bt[5].setMnemonicParsing(false);
    bt[5].setPrefHeight(51);
    bt[5].setPrefWidth(175);
        bt[5].setVisible(false);
        AtomicInteger counter = new AtomicInteger();
    ;
    tf.setOnKeyPressed(e-> {

        if(e.getCode() == KeyCode.SPACE) {

            counter.getAndIncrement();
            if(counter.intValue() == 2){
                String s = tf.getText().trim();
                String split[] = s.split(" ");
                int n = split.length;
                String s1 = new String();
                try {
                    s1 = split[n - 2] + " " + split[n - 1];
                }
                catch (ArrayIndexOutOfBoundsException exception){
                    try {
                        Scenes.mainScene(stage);
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                }
                System.out.println(s1);
               // System.out.println(s);
               // int size = map.get(Driver.helpingMap.get(s)).size();
                System.out.println(Driver.helpingMap.isEmpty());
                for(int j=0;j<bt.length;++j){
                    bt[j].setText("");
                    bt[j].setVisible(false);
                }
                //System.out.println(map.get(Driver.helpingMap.get(s)).peek().getWord());
                if(Driver.helpingMap.containsKey(s1)) {
                    int size = map.get(Driver.helpingMap.get(s1)).size();
                    int i=0;

                    for (Prediction p : map.get(Driver.helpingMap.get(s1))) {
                        if(i < size && i < 6) {
                            bt[i].setText(p.getWord());
                            bt[i].setVisible(true);
                            AtomicBoolean flag1 = new AtomicBoolean(false);
                            String tempString = tf.getText();
                            bt[i].setOnAction(c-> {
                                if(flag1.get() == false) {
                                    tf.setText(tf.getText() + p.getWord());
                                    flag1.set(true);
                                }

                                if(flag1.get() == true){
                                    tf.setText("");
                                    tf.setText(tempString + " " + p.getWord() );
                                }
                            });
                            i++;
                        }
                    }
                }
                counter.set(1);


            }
        }
    });
    tf.setWrapText(true);
    pane.getChildren().addAll(label,tf,bt[0],bt[1],bt[2],bt[3],bt[4],bt[5]);
    Scene scene = new Scene(pane,1070,598);
    stage.setScene(scene);
    scene.getStylesheets().add("style.css");
    pane.setId("pane");

    stage.setTitle("Auto Completion Form");
        stage.initStyle(StageStyle.UNIFIED);
    stage.show();

    }
}
