package testcases.pt.smook;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pages.pt.Pages;
import testbases.pt.TB_Smook;

//Se lanzan errores de tipo: "stale element reference: element is not attached to the page document"
//Cuando se ejecuta esta funcion en paginas que tienen un baner de cuenta regresiva, dado que el banner
//de cuenta regresiva esta creando y eliminando etiquetas de tipo "a"

//Solucion 1 - Ejecutar la prueba cuando no hay Banners con cuenta regresiva
//Solucion 2 - Ejecutar la busqueda de etiquetas "a" solo dentro del contenido intermedio, es decir
//omitiendo las partes superior(donde se encuentran regularmente los banners) e inferior.

public class TC_BrokenLinks extends TB_Smook{
	
	@Test (enabled=true, priority = 1)
	public void homePageBrokenLinksTest(){
		Reporter.log("Starting homePageBrokenLinksTest");
		logger.info("Starting homePageBrokenLinksTest");
		Pages.home();
		Assert.assertTrue(Pages.home().isAt());
		int brokenLinks = Pages.home().checkBrokenLinks();
		assertTrue(brokenLinks<1,"Links Rotos: "+brokenLinks);
	}
	
	@Test (enabled=true, priority = 2)
	public void hotelesBrokenLinksTest(){
		Reporter.log("Starting hotelesBrokenLinksTest");
		logger.info("Starting hotelesBrokenLinksTest");
		Pages.home().goTo();
		Pages.topnav().clickHoteles();
		Assert.assertTrue(Pages.hoteles().isAt());
		int brokenLinks = Pages.hoteles().checkBrokenLinks();
		assertTrue(brokenLinks<1,"Links Rotos: "+brokenLinks);
		logger.info("Ending hotelesBrokenLinksTest");
	}
	
	@Test (enabled=true, priority = 3)
	public void paquetesBrokenLinksTest(){
		Reporter.log("Starting paquetesBrokenLinksTest");
		logger.info("Starting paquetesBrokenLinksTest");
		Pages.home().goTo();
		Pages.topnav().clickPaquetes();
		Assert.assertTrue(Pages.paquetes().isAt());
		int brokenLinks = Pages.paquetes().checkBrokenLinks();
		assertTrue(brokenLinks<1,"Links Rotos: "+brokenLinks);
		logger.info("Ending paquetesBrokenLinksTest");
	}
	
	@Test (enabled=true, priority = 4)
	public void vuelosBrokenLinksTest(){
		Reporter.log("Starting vuelosBrokenLinksTest");
		logger.info("Starting vuelosBrokenLinksTest");
		Pages.home().goTo();
		Pages.topnav().clickVuelos();
		Assert.assertTrue(Pages.vuelos().isAt());
		int brokenLinks = Pages.vuelos().checkBrokenLinks();
		assertTrue(brokenLinks<1,"Links Rotos: "+brokenLinks);
		logger.info("Ending vuelosBrokenLinksTest");
	}
	
	@Test (enabled=true, priority = 5)
	public void trasladosBrokenLinksTest(){
		Reporter.log("Starting trasladosBrokenLinksTest");
		logger.info("Starting trasladosBrokenLinksTest");
		Pages.home().goTo();
		Pages.topnav().clickTraslados();
		Assert.assertTrue(Pages.traslados().isAt());
		int brokenLinks = Pages.traslados().checkBrokenLinks();
		assertTrue(brokenLinks<1,"Links Rotos: "+brokenLinks);
		logger.info("Ending trasladosBrokenLinksTest");
	}
	
	@Test (enabled=true, priority = 6)
	public void toursBrokenLinksTest(){
		Reporter.log("Starting toursBrokenLinksTest");
		logger.info("Starting toursBrokenLinksTest");
		Pages.home().goTo();
		Pages.topnav().clickTours();
		Assert.assertTrue(Pages.tours().isAt());
		int brokenLinks = Pages.tours().checkBrokenLinks();
		assertTrue(brokenLinks<1,"Links Rotos: "+brokenLinks);
		logger.info("Ending toursBrokenLinksTest");
	}
	
	@Test (enabled=true, priority = 7)
	public void autosBrokenLinksTest(){
		Reporter.log("Starting autosBrokenLinksTest");
		logger.info("Starting autosBrokenLinksTest");
		Pages.home().goTo();
		Pages.topnav().clickAutos();
		Assert.assertTrue(Pages.autos().isAt());
		int brokenLinks = Pages.autos().checkBrokenLinks();
		assertTrue(brokenLinks<1,"Links Rotos: "+brokenLinks);
		logger.info("Ending autosBrokenLinksTest");
	}
	
	@Test (enabled=true, priority = 8)
	public void autobusesBrokenLinksTest(){
		Reporter.log("Starting autobusesBrokenLinksTest");
		logger.info("Starting autobusesBrokenLinksTest");
		Pages.home().goTo();
		Pages.topnav().clickAutobuses();
		Assert.assertTrue(Pages.autobuses().isAt());
		int brokenLinks = Pages.autobuses().checkBrokenLinks();
		assertTrue(brokenLinks<1,"Links Rotos: "+brokenLinks);
		logger.info("Ending autobusesBrokenLinksTest");
	}
	
	@Test (enabled=true, priority = 9)
	public void crucerosBrokenLinksTest(){
		Reporter.log("Starting crucerosBrokenLinksTest");
		logger.info("Starting crucerosBrokenLinksTest");
		Pages.home().goTo();
		Pages.topnav().clickCruceros();
		Assert.assertTrue(Pages.cruceros().isAt());
		int brokenLinks = Pages.cruceros().checkBrokenLinks();
		assertTrue(brokenLinks<1,"Links Rotos: "+brokenLinks);
		logger.info("Ending crucerosBrokenLinksTest");
	}
	
	@Test (enabled=true, priority = 10)
	public void ofertasBrokenLinksTest(){
		Reporter.log("Starting ofertasBrokenLinksTest");
		logger.info("Starting ofertasBrokenLinksTest");
		Pages.home().goTo();
		Pages.topnav().clickOfertas();
		Assert.assertTrue(Pages.ofertas().isAt());
		int brokenLinks = Pages.ofertas().checkBrokenLinks();
		assertTrue(brokenLinks<1,"Links Rotos: "+brokenLinks);
		logger.info("Ending ofertasBrokenLinksTest");
	}
	
	@Test (enabled=true, priority = 11)
	public void gruposBrokenLinksTest(){
		Reporter.log("Starting gruposBrokenLinksTest");
		logger.info("Starting gruposBrokenLinksTest");
		Pages.home().goTo();
		Pages.topnav().clickGrupos();
		Assert.assertTrue(Pages.grupos().isAt());
		int brokenLinks = Pages.grupos().checkBrokenLinks();
		assertTrue(brokenLinks<1,"Links Rotos: "+brokenLinks);
		logger.info("Ending gruposBrokenLinksTest");
	}
	
	@Test (enabled=true, priority = 12)
	public void attentionPointsBrokenLinksTest(){
		Reporter.log("Starting attentionPointsBrokenLinksTest");
		logger.info("Starting attentionPointsBrokenLinksTest");
		Pages.home().goTo();
		Assert.assertTrue(Pages.home().isAt());
		Pages.topnav().clickPuntosDeAtencion();
		Assert.assertTrue(Pages.attentionPoints().isAt());
		int brokenLinks = Pages.attentionPoints().checkBrokenLinks();
		assertTrue(brokenLinks<1,"Links Rotos: "+brokenLinks);
		logger.info("Ending attentionPointsBrokenLinksTest");
	}
	
	@Test (enabled=true, priority = 13)
	public void revistaBrokenLinksTest(){
		Reporter.log("Starting revistaBrokenLinksTest");
		logger.info("Starting revistaBrokenLinksTest");
		Pages.home().goTo();
		Assert.assertTrue(Pages.home().isAt());
		Pages.topnav().clickRevista();
		Assert.assertTrue(Pages.revista().isAt());
		int brokenLinks = Pages.revista().checkBrokenLinks();
		assertTrue(brokenLinks<1,"Links Rotos: "+brokenLinks);
		logger.info("Ending revistaBrokenLinksTest");
	}
	
	@Test (enabled=true, priority = 14)
	public void consultaItinerarioBrokenLinksTest(){
		Reporter.log("Starting consultaItinerarioBrokenLinksTest");
		logger.info("Starting consultaItinerarioBrokenLinksTest");
		Pages.home().goTo();
		Assert.assertTrue(Pages.home().isAt());
		Pages.topnav().clickConsultaItinerario();
		Assert.assertTrue(Pages.consultaItinerario().isAt());
		int brokenLinks = Pages.consultaItinerario().checkBrokenLinks();
		assertTrue(brokenLinks<1,"Links Rotos: "+brokenLinks);
		logger.info("Ending consultaItinerarioBrokenLinksTest");
	}
	
	@Test (enabled=true, priority = 15)
	public void ayudaBrokenLinksTest(){
		Reporter.log("Starting ayudaBrokenLinksTest");
		logger.info("Starting ayudaBrokenLinksTest");
		Pages.home().goTo();
		Assert.assertTrue(Pages.home().isAt());
		Pages.topnav().clickAyuda();
		Assert.assertTrue(Pages.ayuda().isAt());
		int brokenLinks = Pages.ayuda().checkBrokenLinks();
		assertTrue(brokenLinks<1,"Links Rotos: "+brokenLinks);
		logger.info("Ending ayudaBrokenLinksTest");
	}
	
	@Test (enabled=true, priority = 16)
	public void iniciarSesionBrokenLinksTest(){
		Reporter.log("Starting iniciarSesionBrokenLinksTest");
		logger.info("Starting iniciarSesionBrokenLinksTest");
		Pages.home().goTo();
		Assert.assertTrue(Pages.home().isAt());
		Pages.topnav().clickIniciarSesion();
		Assert.assertTrue(Pages.iniciarSesion().isAt());
		int brokenLinks = Pages.iniciarSesion().checkBrokenLinks();
		assertTrue(brokenLinks<1,"Links Rotos: "+brokenLinks);
		logger.info("Ending iniciarSesionBrokenLinksTest");
	}
	
	//++++++++++ Pruebas del Search ++++++++++
	
	@Test (enabled=true, priority = 17)
	public void searchBrokenLinksTest(){
		Reporter.log("Starting searchBrokenLinksTest");
		logger.info("Starting searchBrokenLinksTest");
		Pages.home().goTo();
		Assert.assertTrue(Pages.home().isAt());
		Pages.topnav().typeAndSubmitSearch("cancun");
		Assert.assertTrue(Pages.searchResults().isAt());
		int brokenLinks = Pages.searchResults().checkBrokenLinks();
		assertTrue(brokenLinks<1,"Links Rotos: "+brokenLinks);
		logger.info("Ending searchBrokenLinksTest");
	}
}
