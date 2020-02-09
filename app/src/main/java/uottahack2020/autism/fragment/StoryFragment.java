package uottahack2020.autism.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import uottahack2020.autism.ActivityId;
import uottahack2020.autism.MainActivity;
import uottahack2020.autism.R;
import uottahack2020.autism.Session;
import uottahack2020.autism.controller.StoryCtrl;
import uottahack2020.autism.model.Story;

public class StoryFragment extends Fragment<StoryCtrl> {
    public static final String TAG = "StoryFragment";
    private static final int LAYOUT_ID = R.layout.fragment_story;
    private static final String ACTIVITY_TAG = MainActivity.TAG;

    public static FragmentId setupId(ActivityId activityId) {
        return FragmentId.SET(StoryFragment.class, TAG, LAYOUT_ID, activityId, true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (controller == null) {
            controller = new StoryCtrl(FragmentActivity.getSavedInstance(getFragmentId().getDefaultActivityId(), this));
        }
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(getFragmentId().getLayoutId(), container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        controller.setStory((Story) Session.CURRENT_QUEST.findActionPoint(Story.class).getRoadBlock());
        controller.init(view);
        controller.updateInfo();
    }

    @Override
    public FragmentId getFragmentId() {
        return setupId(ActivityId.GET(ACTIVITY_TAG));
    }

    @Override
    public boolean onHomeUpPressed() {
        return false;
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }
}

