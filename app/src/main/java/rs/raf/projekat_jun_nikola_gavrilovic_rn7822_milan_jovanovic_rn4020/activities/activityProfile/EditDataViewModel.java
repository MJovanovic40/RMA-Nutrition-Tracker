package rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.activities.activityProfile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import rs.raf.projekat_jun_nikola_gavrilovic_rn7822_milan_jovanovic_rn4020.database.entities.UserEntity;

public class EditDataViewModel extends ViewModel {

    private MutableLiveData<UserEntity> userEntityLiveData;

    public EditDataViewModel() {
        userEntityLiveData = new MutableLiveData<>();
    }

    public LiveData<UserEntity> getUserEntityLiveData() {
        return userEntityLiveData;
    }

    public void setUserEntity(UserEntity userEntity) {
        userEntityLiveData.setValue(userEntity);
    }

    public UserEntity getUserEntity() {
        return userEntityLiveData.getValue();
    }
}
