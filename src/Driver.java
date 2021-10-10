import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Driver extends Application {
    public static HashMap<String, Predictor> helpingMap;
    public void start(Stage primaryStage) throws Exception {
        Scenes.mainScene(primaryStage);

    }
    public static void main(String[] args) {
        launch(args);
    }




    public static HashMap<Predictor,PriorityQueue<Prediction>> readFile() throws FileNotFoundException {
        helpingMap = new HashMap<>();
        HashMap<Predictor, PriorityQueue<Prediction>> map = new HashMap<>();
        Scanner inputFile = new Scanner(new File("C:\\Users\\Alqarout\\Desktop\\untitled\\src\\Data"));
        while(inputFile.hasNextLine()){
            String line = inputFile.nextLine();
           // System.out.println(line);
            String split[] = line.split(" ");

            for (int i = 0; i < split.length-2; i++) {
                String twoWords = split[i] + " " + split[i+1];
                if(!helpingMap.containsKey(twoWords)){
                    Predictor predictor = new Predictor(twoWords);
                    helpingMap.put(twoWords,predictor);
                    map.put(predictor,new PriorityQueue<Prediction>());
                    Prediction prediction = new Prediction(split[i+2]);
                    prediction.increaseFrequency();
                    map.get(predictor).add(prediction);
                }
                else if(helpingMap.containsKey(twoWords)){
                    Predictor predictor = helpingMap.get(twoWords);
                    boolean flag = false;
                    for(Prediction p : map.get(predictor)){
                        if(p.getWord().equals(split[i+2])){
                            p.increaseFrequency();
                            flag = true;
                            break;
                        }
                    }
                    if (flag == false){
                        Prediction prediction = new Prediction(split[i+2]);
                        prediction.increaseFrequency();
                        map.get(predictor).add(prediction);
                    }
                }

            }

        }
        System.out.println(helpingMap.isEmpty());
        return map;
    }
}
