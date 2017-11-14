package org.provaHubFintech.server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class DatabaseServerApplication extends Application {

	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(getContext());
		
		router.attach("/conta", ContaServerResource.class);
		router.attach("/conta/delete", ContaDeleteServerResource.class);
		router.attach("/conta/update", ContaUpdateServerResource.class);
		router.attach("/pessoafisica", PessoaFisicaServerResource.class);
		router.attach("/pessoafisica/delete", PessoaFisicaDeleteServerResource.class);
		router.attach("/pessoafisica/update", PessoaFisicaUpdateServerResource.class);
		router.attach("/pessoajuridica", PessoaJuridicaServerResource.class);
		router.attach("/pessoajuridica/delete", PessoaJuridicaDeleteServerResource.class);
		router.attach("/pessoajuridica/update", PessoaJuridicaUpdateServerResource.class);
		router.attach("/transferencia", TransferenciaServerResource.class);
		router.attach("/transferencia/delete", TransferenciaDeleteServerResource.class);
		router.attach("/transferencia/update", TransferenciaUpdateServerResource.class);
		
		return router;
	}
}
