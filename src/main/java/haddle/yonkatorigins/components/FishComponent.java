package haddle.yonkatorigins.components;

import dev.onyxstudios.cca.api.v3.component.Component;

public interface FishComponent extends Component {
    double fish = 0;
    double maxFish = 100;
    void setMaxFish(double max);
    void changeFish(double amount);
    double getMaxFish();
    double getFish();
}
