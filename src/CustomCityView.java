import becker.robots.*;
import java.awt.*;

/**
 */
public class CustomCityView extends CityView{
    private City aModel;
    private Rectangle roads;
    private int simSize;

    public CustomCityView(City aModel, Rectangle roads, int simSize){
        super(aModel, roads, simSize);
    }

    public CustomCityView(CustomCityView customCityView){
        super(customCityView.getaModel(), customCityView.getRoads(), customCityView.getSimSize());
    }



    public City getaModel(){ return this.aModel; }
    public Rectangle getRoads(){ return this.roads; }
    public int getSimSize(){ return this.simSize; }
}

