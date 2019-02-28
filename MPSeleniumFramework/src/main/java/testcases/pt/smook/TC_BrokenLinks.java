package testcases.pt.smook;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pages.pt.Pages;
import testbases.pt.TB_Smook;

public class TC_BrokenLinks extends TB_Smook{

	@Test (enabled=false, priority = 1)
	public void goToHomePageTest(){
		Reporter.log("Starting GoToHomePageTest");
		logger.info("Starting GoToHomePageTest");
		Pages.home();
		Assert.assertTrue(Pages.home().isAt());
	}
	
	@Test (enabled=false, priority = 2)
	public void goToHotelesTest(){
		Reporter.log("Starting GoToHotelesTest");
		logger.info("Starting GoToHotelesTest");
		Pages.home().goTo();
		Pages.topnav().clickHoteles();
		Assert.assertTrue(Pages.hoteles().isAt());
		logger.info("Ending GoToHotelesTest");
	}
	
	@Test (enabled=false, priority = 3)
	public void goToPaquetesTest(){
		Reporter.log("Starting GoToPaquetesTest");
		logger.info("Starting GoToPaquetesTest");
		Pages.home().goTo();
		Pages.topnav().clickPaquetes();
		Assert.assertTrue(Pages.paquetes().isAt());
		logger.info("Ending GoToPaquetesTest");
	}
	
	@Test (enabled=false, priority = 4)
	public void goToVuelosTest(){
		Reporter.log("Starting GoToVuelosTest");
		logger.info("Starting GoToVuelosTest");
		Pages.home().goTo();
		Pages.topnav().clickVuelos();
		Assert.assertTrue(Pages.vuelos().isAt());
		logger.info("Ending GoToVuelosTest");
	}
	
	@Test (enabled=false, priority = 5)
	public void goToTrasladosTest(){
		Reporter.log("Starting GoToTrasladosTest");
		logger.info("Starting GoToTrasladosTest");
		Pages.home().goTo();
		Pages.topnav().clickTraslados();
		Assert.assertTrue(Pages.traslados().isAt());
		logger.info("Ending GoToPaquetesTest");
	}
	
	@Test (enabled=false, priority = 6)
	public void goToToursTest(){
		Reporter.log("Starting GoToToursTest");
		logger.info("Starting GoToToursTest");
		Pages.home().goTo();
		Pages.topnav().clickTours();
		Assert.assertTrue(Pages.tours().isAt());
		logger.info("Ending GoToToursTest");
	}
	
	@Test (enabled=false, priority = 7)
	public void goToAutosTest(){
		Reporter.log("Starting GoToAutosTest");
		logger.info("Starting GoToAutosTest");
		Pages.home().goTo();
		Pages.topnav().clickAutos();
		Assert.assertTrue(Pages.autos().isAt());
		logger.info("Ending GoToAutosTest");
	}
	
	@Test (enabled=true, priority = 8)
	public void goToAutobusesTest(){
		Reporter.log("Starting GoToAutobusesTest");
		logger.info("Starting GoToAutobusesTest");
		Pages.home().goTo();
		Pages.topnav().clickAutobuses();
		Assert.assertTrue(Pages.autobuses().isAt());
		int brokenLinks = Pages.autobuses().checkBrokenLinks();
		assertTrue(brokenLinks<1,"Links Rotos: "+brokenLinks);
		logger.info("Ending GoToAutobusesTest");
	}
	
	@Test (enabled=true, priority = 9)
	public void goToCrucerosTest(){
		Reporter.log("Starting GoToCrucerosTest");
		logger.info("Starting GoToCrucerosTest");
		Pages.home().goTo();
		Pages.topnav().clickCruceros();
		Assert.assertTrue(Pages.cruceros().isAt());
		logger.info("Ending GoToCrucerosTest");
	}
	
	@Test (enabled=true, priority = 10)
	public void goToOfertasTest(){
		Reporter.log("Starting GoToOfertasTest");
		logger.info("Starting GoToOfertasTest");
		Pages.home().goTo();
		Pages.topnav().clickOfertas();
		Assert.assertTrue(Pages.ofertas().isAt());
		logger.info("Ending GoToOfertasTest");
	}
	
	@Test (enabled=true, priority = 11)
	public void goToGruposTest(){
		Reporter.log("Starting GoToGruposTest");
		logger.info("Starting GoToGruposTest");
		Pages.home().goTo();
		Pages.topnav().clickGrupos();
		Assert.assertTrue(Pages.grupos().isAt());
		logger.info("Ending GoToGruposTest");
	}
	
	@Test (enabled=true, priority = 12)
	public void goToAttentionPointsTest(){
		Reporter.log("Starting goToAttentionPointsTest");
		logger.info("Starting goToAttentionPointsTest");
		Pages.home().goTo();
		Assert.assertTrue(Pages.home().isAt());
		Pages.topnav().clickPuntosDeAtencion();
		Assert.assertTrue(Pages.AttentionPoints().isAt());
		logger.info("Ending goToAttentionPointsTest");
	}
	
	@Test (enabled=true, priority = 13)
	public void goToRevistaTest(){
		Reporter.log("Starting goToRevistaTest");
		logger.info("Starting goToRevistaTest");
		Pages.home().goTo();
		Assert.assertTrue(Pages.home().isAt());
		Pages.topnav().clickRevista();
		Assert.assertTrue(Pages.Revista().isAt());
		logger.info("Ending goToRevistaTest");
	}
	
	@Test (enabled=true, priority = 14)
	public void goToConsultaItinerarioTest(){
		Reporter.log("Starting goToConsultaItinerarioTest");
		logger.info("Starting goToConsultaItinerarioTest");
		Pages.home().goTo();
		Assert.assertTrue(Pages.home().isAt());
		Pages.topnav().clickConsultaItinerario();
		Assert.assertTrue(Pages.ConsultaItinerario().isAt());
		logger.info("Ending goToConsultaItinerarioTest");
	}
	
	@Test (enabled=true, priority = 15)
	public void goToAyudaTest(){
		Reporter.log("Starting goToAyudaTest");
		logger.info("Starting goToAyudaTest");
		Pages.home().goTo();
		Assert.assertTrue(Pages.home().isAt());
		Pages.topnav().clickAyuda();
		Assert.assertTrue(Pages.Ayuda().isAt());
		logger.info("Ending goToAyudaTest");
	}
	
	@Test (enabled=true, priority = 16)
	public void goToIniciarSesionTest(){
		Reporter.log("Starting goToIniciarSesionTest");
		logger.info("Starting goToIniciarSesionTest");
		Pages.home().goTo();
		Assert.assertTrue(Pages.home().isAt());
		Pages.topnav().clickIniciarSesion();
		Assert.assertTrue(Pages.IniciarSesion().isAt());
		logger.info("Ending goToIniciarSesionTest");
	}
	
	//++++++++++ Pruebas del Search ++++++++++
	
	@Test (enabled=true, priority = 17)
	public void searchTest(){
		Reporter.log("Starting searchTest");
		logger.info("Starting searchTest");
		Pages.home().goTo();
		Assert.assertTrue(Pages.home().isAt());
		Pages.topnav().typeAndSubmitSearch("cancun");
		Assert.assertTrue(Pages.SearchResults().isAt());
		logger.info("Ending searchTest");
	}
}
