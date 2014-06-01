import com.badlogic.gdx.backends.jogl.JoglApplication;


public class MainAct {
	public static void main(String args[])
	{
		JoglApplication jApp = new JoglApplication(new AppListen(), "UpdatePang",900, 550, false);
		
	}
}
