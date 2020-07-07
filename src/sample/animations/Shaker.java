package sample.animations;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shaker {
    private TranslateTransition translateTransition;

    public  Shaker(Node node){
        // how long does each shakes takes time:
        translateTransition = new TranslateTransition(Duration.millis(50), node);
        //the begging and the place that it will return after shaking
        translateTransition.setFromX(0f);
        //how much does it move :
        translateTransition.setByX(20f);
//      translateTransition.setByY(23);
        translateTransition.setCycleCount(2);
        translateTransition.setAutoReverse(true);

    }


    public void shake(){
        translateTransition.playFromStart();
    }
}
