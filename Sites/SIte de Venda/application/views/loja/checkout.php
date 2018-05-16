	<header class="intro">
		<div class="mid">
			<div class="container">
				<h3>Checkout</h3>
			</div>
		</div>
	</header>
	<div class="content-wrapper">
		<div class="blank">
			<div class="row">
			<?php
			$CI =& get_instance();
			$CI->load->model('client_model');
                foreach ($dados as $dado) {
                    $result = $CI->client_model->getPluginUserAndIdPlugin($this->my_auth->get('user', 'id'),$dado->id);
                    if(sizeof($result) == 0){
                        echo '<div class="col-md-3">
					<h3 class="text-center">'.$dado->nome.'</h3>
					<br> <img class="img-fluid"
						src="'.asset_url().'images/utils/plugin.png"
						style="max-width: 150px; position: relative; left: 50%; transform: translateX(-50%)">
					<br>
					<div>
						<p>
							Valor: <span class="float-right" style="color: #4caf50;">'.$dado->preco.',00</span>
						</p>
						<br>
                        '.form_button("detalhe", "DETALHES", array(
                            "name" => "detalhe",
                            "id" => "button",
                            "value" => "true",
                            "type" => "reset",
                            "content" => "DETALHES",
                            "class" => "form-control btn btn-primary",
                            "onclick" => "window.location.href='/loja/detalhes/" . $dado->nome . "'")).'
					</div>
				</div>
				<div class="col-md-6">
					<h4>Informações importantes</h4>
					<br>
					<div class="alert alert-info">
						<i class="ion-alert"></i> Este plugin será liberado somente nesta
						conta '.$this->my_auth->get('user', 'username').'.
					</div>
					<p>
						Ao comprar este plugin, ele é liberado no seu painel a partir do
						momento que o pagamento for aprovado. <br> Assim que disponível, é
						necessário entrar no painel e fazer a alteração para a liberação
						do IP do <b>seu servidor</b>. Caso haja alguma dúvida é de extrema
						importância e direito seu entrar em contato através da área
						ticket. <br>
						<br> Caso desejar pagar em dinheiro, pode optar por depósito
						bancário ao invez de boleto, fazendo o mesmo direto numa
						lotérica,banco e até pela internet caso sua conta seja da caixa. <br>
						<br> <b>Conta Bancária:</b><br> Caixa Economica Poupanca - 013<br>
						Alexandre Luiz de Souza Ferreira<br> Conta 6870-9<br> Agencia 0997<br>
						<br> Caso pagar por depósito, envie o comprovante para um dos
						meios de contato <br> Email: tintacontato@gmail.com<br> Skype:
						alexandre.ferreira249<br>
					</p>
				</div>
				<div class="col-md-3">
					<div class="bg">
						<h3>Pagar</h3>
						<br>
                        '.form_open("loja/pay").'
							<input type="hidden" name="id" value="'.$dado->id.'">
							<div class="row gateways">
								<div class="col-md-12">
									<label> <input type="radio" name="gateway" value="pagseguro"
										checked=""> <img src="https://i.imgur.com/3dozPs9.png"
										alt="TintaDev" class="img-fluid">
									</label>
								</div>
								<div class="col-md-12">
									<label> <input type="radio" name="gateway" value="paypal"> <img
										src="https://i.imgur.com/w1cFbRq.png" alt="TintaDev"
										class="img-fluid">
									</label>
								</div>
								<div class="col-md-12">
									<label> <input type="radio" name="gateway" value="mercadopago">
										<img src="https://i.imgur.com/04bzV3e.png" alt="TintaDev"
										class="img-fluid">
									</label>
								</div>
							</div>
							<div class="form-group">
								<label class="custom-control custom-checkbox"> <input
									type="checkbox" class="custom-control-input" required=""> <span
									class="custom-control-indicator"></span> <span
									class="custom-control-description">Eu aceito os <a
										href="javascript:void(0)" id="termos-de-uso">termos de compra</a></span>
								</label>
							</div>
							<button class="btn btn-success form-control">PAGAR</button>
                       '.form_close().'
					</div>
				</div>
				<div class="termos">
					<div class="content">
						<div class="header">
							termos de uso
							<button id="termos-close">
								<i class="ion-close"></i>
							</button>
						</div>
						<div class="body">
							<p>
								Esta comprando um produto digital, e declara que após a TintaDev
								liberar o produto, o mesmo não pode ser trocado ou reembolsado.<br>
								<br>Produto sera adicionado automaticamente em sua conta após a
								aprovação do pagamento, para pagamentos por depósito o mesmo
								sera entregue após recebermos o comprovante de pagamento.<br>
								<br>Suporte dos produtos é feito de acordo com a disponibilidade
								de atendimento, e cada caso é analisado e tratado de uma maneira
								diferente, assim tendo casos prioritários.<br>
								<br>Em casos de chargeback, o sistema automaticamente ira tirar
								o produto da conta e congelar os outros até a resolução do caso.<br>
								<br>O usuário tem direito a 2 licenças somente, designada para o
								seu próprio servidor, qualquer caso de compartilhamento de
								licenças a conta sera desabilitada permanentemente.
							</p>
						</div>
					</div>
				</div>';
                    }
                    else{
                        redirect("/");
                    }
                }
            ?>
				
			</div>
		</div>
	</div>