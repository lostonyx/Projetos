	<header class="intro">
		<div class="mid">
			<div class="container">
				<h3>Criadores / Ver</h3>
			</div>
		</div>
	</header>
	<div class="content-wrapper">
		<div class="blank">
			<div class="row">
			<?php
			 foreach ($criadors as $criador){
    			    echo '
    				<div class="col-md-3">
    					<h3 class="text-center">'.$criador->nome.'</h3>
    					<br> <img class="img-fluid"
    						src="'.asset_url().'images/utils/plugin.png"
    						style="max-width: 150px; position: relative; left: 50%; transform: translateX(-50%)">
    					<br>
                        <div>
                        <p>
							MercadoPago: <span class="float-right" style="color: #4caf50;">'.($criador->mercadopago == 0 ? "Desligado" : "Ligado").'</span>
						</p>
                        <p>
							PagSeguro: <span class="float-right" style="color: #4caf50;">'.($criador->pagseguro == 0 ? "Desligado" : "Ligado").'</span>
						</p>
                        <div>
                        <h3>MercadoPago</h3>
                        <p>
							client_id: <span class="float-right" style="color: #4caf50;">'.$criador->mercadopagoID.'</span>
						</p>
                        <p>
							client_secret: <span class="float-right" style="color: #4caf50;">'.$criador->mercadopagoSECRET.'</span>
						</p>
                        </div>
                        <div>
                        <h3>PagSeguro</h3>
                        <p>
							Email: <span class="float-right" style="color: #4caf50;">'.$criador->pagseguroEmail.'</span>
						</p>
                        <p>
							Token: <span class="float-right" style="color: #4caf50;">'.$criador->pagseguroToken.'</span>
						</p>
                        </div>
                        <div>
                        <h3>PayPal(Em Breve)</h3>
                        <p>
							client_id: <span class="float-right" style="color: #4caf50;">????</span>
						</p>
                        <p>
							client_secret: <span class="float-right" style="color: #4caf50;">????</span>
						</p>
                        </div>
                        </div>
    				</div>
                    <div class="col-md-3">
    					<div class="bg">
    						<h3>ACOES RAPIDAS</h3>
    						<br>
                            '.form_button("editar", "EDITAR", array(
                            "id" => "button",
                            "value" => "true",
                            "type" => "reset",
                            "class" => "form-control btn btn-primary",
                                "onclick" => "window.location.href='/admin/criadores/edit/".$criador->id."'")).'
                            <br><br>
                            '.form_button("deletar", "DELETAR", array(
                            "id" => "button",
                            "value" => "true",
                            "type" => "reset",
                            "class" => "form-control btn btn-primary",
                                "onclick" => "window.location.href='/admin/criadores/del/".$criador->id."'")).'
                            <br><br>
                            '.form_button("menu", "IR /P PAINEL", array(
                            "id" => "button",
                            "value" => "true",
                            "type" => "reset",
                            "class" => "form-control btn btn-primary",
                                "onclick" => "window.location.href='/admin/criadores/'")).'
    					</div>
    				</div>';	
    			}
    			
			?>
			</div>
		</div>
	</div>