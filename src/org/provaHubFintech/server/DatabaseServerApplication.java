package org.provaHubFintech.server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class DatabaseServerApplication extends Application {

	public Restlet createInBoundRoot() {
		Router router = new Router(getContext());
		
		router.attach("/database/conta", ContaServerResource.class);
		router.attach("/database/pessoafisica", PessoaFisicaServerResource.class);
		router.attach("/database/pessoajuririca", PessoaJuridicaServerResource.class);
		router.attach("/databse/transferencia", TransferenciaServerResource.class);
		
		return router;
	}
}