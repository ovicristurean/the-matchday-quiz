import androidx.compose.ui.window.ComposeUIViewController
import com.ovidiucristurean.thematchdayquiz.App
import platform.Foundation.NSBundle
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle

fun MainViewController() = ComposeUIViewController {
    val isDarkTheme =
        UIScreen.mainScreen.traitCollection.userInterfaceStyle() == UIUserInterfaceStyle.UIUserInterfaceStyleDark
    App(
        darkTheme = isDarkTheme,
        resourcePath = NSBundle.mainBundle.resourcePath.toString()
    )
}
