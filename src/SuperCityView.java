//import becker.robots.*;
//import java.awt.*;
//
///**
// * Created by Iso on 10-Nov-15.
// */
//public class SuperCityView extends CustomCityView {
//    private City aModel;
//    private Rectangle roads;
//    private int simSize;
//    CustomCityView customCityView = new CustomCityView(aModel, roads, simSize);
//    City pacCity = new City();
//    RobotUIComponents components = new RobotUIComponents(pacCity, 0, 0, 10, 10);
//
//    public SuperCityView(City aModel, Rectangle roads, int simSize) {
//        super(aModel, roads, simSize);
//    }
//
//    public SuperCityView(CustomCityView customCityView) {
//        super(customCityView.getaModel(), customCityView.getRoads(), customCityView.getSimSize());
//    }
//
//    public SuperCityView(){
//        super(components.getCityView());
//    }
//
//    public CityView getCityView() {
//        return this.components.getCityView();
//    }
//
//}
