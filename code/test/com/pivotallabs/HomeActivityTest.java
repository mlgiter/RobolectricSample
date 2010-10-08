package com.pivotallabs;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.xtremelabs.droidsugar.fakes.FakeActivity;
import com.xtremelabs.droidsugar.fakes.FakeIntent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.pivotallabs.TestHelper.proxyFor;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(FastAndroidTestRunner.class)
public class HomeActivityTest {
    private TextView pressMeButton;
    private ImageView pivotalLogo;
    private HomeActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = new HomeActivity();
        activity.onCreate(null);
        pressMeButton = (TextView) activity.findViewById(R.id.press_me_button_id);
        pivotalLogo = (ImageView) activity.findViewById(R.id.pivotal_logo);
    }

    @Test
    public void shouldHaveAButtonThatSaysPressMe() throws Exception {
        assertThat((String) pressMeButton.getText(), equalTo("Tests Rock!"));
    }

    @Test
    public void pressingTheButtonShouldStartTheListActivity() throws Exception {
        pressMeButton.performClick();

        FakeActivity fakeActivity = proxyFor(activity);
        Intent startedIntent = fakeActivity.getNextStartedIntent();
        FakeIntent fakeIntent = proxyFor(startedIntent);
        Class<NamesActivity> actualStartedActivityClass = (Class<NamesActivity>) fakeIntent.componentClass;

        assertThat(actualStartedActivityClass, equalTo(NamesActivity.class));
    }

    @Test
    public void shouldHaveALogo() throws Exception {
        assertThat(pivotalLogo.getVisibility(), equalTo(View.VISIBLE));
        assertThat(proxyFor(pivotalLogo).resourceId, equalTo(R.drawable.pivotallabs_logo));
    }
}
