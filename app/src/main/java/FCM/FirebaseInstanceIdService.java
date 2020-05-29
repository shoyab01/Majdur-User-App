package FCM;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;

import static android.content.ContentValues.TAG;

public class FirebaseInstanceIdService extends FirebaseMessagingService {


    @Override
    public void onNewToken(String token) {
        Log.d("token", "ttttttttttttttttttttttttttttt: " + token);
        //sendRegistrationToServer(token);
    }

}

