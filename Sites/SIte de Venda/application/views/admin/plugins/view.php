	<header class="intro">
		<div class="mid">
			<div class="container">
				<h3>Plugins / Ver</h3>
			</div>
		</div>
	</header>
	<div class="content-wrapper">
		<div class="blank">
			<div class="row">
			<?php
			$CI =& get_instance();
			$CI->load->model('plugin_model');
    			foreach ($plugins as $plugin){
    			    $result = $CI->plugin_model->getCriador($plugin->criador_id);
    			    $dados = $result[0];
    			    echo '
    				<div class="col-md-3">
    					<h3 class="text-center">'.$plugin->nome.'</h3>
    					<br> <img class="img-fluid"
    						src="'.asset_url().'images/utils/plugin.png"
    						style="max-width: 150px; position: relative; left: 50%; transform: translateX(-50%)">
    					<br>
                        <div>
                        <p>
							Valor: <span class="float-right" style="color: #4caf50;">'.$plugin->preco.',00</span>
						</p>
                        <p>
							Pago: <span class="float-right" style="color: #4caf50;">'.($plugin->pago == 0 ? "Sim" : "Nao").'</span>
						</p>
                        <p>
							Tipo: <span class="float-right" style="color: #4caf50;">'.($plugin->tipo == 0 ? "ReflectionLoad" : "OneClass").'</span>
						</p>
                        <p>
							Criador: <span class="float-right" style="color: #4caf50;">'.$dados->nome.'</span>
						</p>
                        <div>
                        <h3>MercadoPago</h3>
                        <p>
							client_id: <span class="float-right" style="color: #4caf50;">'.$dados->mercadopagoID.'</span>
						</p>
                        <p>
							client_secret: <span class="float-right" style="color: #4caf50;">'.$dados->mercadopagoSECRET.'</span>
						</p>
                        </div>
                        <div>
                        <h3>PagSeguro</h3>
                        <p>
							Email: <span class="float-right" style="color: #4caf50;">'.$dados->pagseguroEmail.'</span>
						</p>
                        <p>
							Token: <span class="float-right" style="color: #4caf50;">'.$dados->pagseguroToken.'</span>
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
    				<div class="col-md-6">
    										<div class="bg">
    						<h3>Descricao</h3>
    						<br>
    							<div class="row">
    								'.$plugin->descricao.'
    							</div>
    							<br>
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
                                "onclick" => "window.location.href='/admin/plugins/edit/".$plugin->id."'")).'
                            <br><br>
                            '.form_button("deletar", "DELETAR", array(
                            "id" => "button",
                            "value" => "true",
                            "type" => "reset",
                            "class" => "form-control btn btn-primary",
                                "onclick" => "window.location.href='/admin/plugins/del/".$plugin->id."'")).'
                            <br><br>
                            '.form_button("baixar", "BAIXAR", array(
                            "id" => "button",
                            "value" => "true",
                            "type" => "reset",
                            "class" => "form-control btn btn-primary",
                                "onclick" => "window.location.href='/admin/plugins/baixar/".$plugin->id."'")).'
                            <br><br>
                            '.form_button("menu", "IR /P PAINEL", array(
                            "id" => "button",
                            "value" => "true",
                            "type" => "reset",
                            "class" => "form-control btn btn-primary",
                                "onclick" => "window.location.href='/admin/plugins/'")).'
    					</div>
    				</div>';	
    			}
    			
			?>
			</div>
		</div>
	</div>