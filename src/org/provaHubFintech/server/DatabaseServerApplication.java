package org.provaHubFintech.server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class DatabaseServerApplication extends Application {

	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(getContext());
		
		router.attach("/conta", ContaServerResource.class);
		router.attach("/pessoafisica", PessoaFisicaServerResource.class);
		router.attach("/pessoajuridica", PessoaJuridicaServerResource.class);
		router.attach("/transferencia", TransferenciaServerResource.class);
		
		return router;
	}
}
