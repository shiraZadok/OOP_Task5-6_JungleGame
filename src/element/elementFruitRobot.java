package element;
import dataStructure.node_data;

public interface elementFruitRobot {

    public elementFruitRobot init(String json);

    /**
     * @return a string that represent the picture of the fruit\robot.
     */
    public String get_pic();

    /**
     *
     * @param json
     */
    public void set_pic(String json);
}
