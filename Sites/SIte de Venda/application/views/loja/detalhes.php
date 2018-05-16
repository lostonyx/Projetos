<header class="intro">
	<div class="mid">
		<div class="container"></div>
	</div>
</header>
<div class="content-wrapper">
	<div class="blank">
		<h4 class="no-margin">Saiba mais sobre o plugin</h4>
	</div>
	<div class="bg">
		<div class="row">
			<?php
                foreach ($dados as $dado) {
                    echo '<div class="col-md-4">
                    <div class="card">
                    <div class="card-block">
                    <h3 class="text-center">'.$dado->nome.'</h3>
							<img class="img-fluid"
								src="'.asset_url().'images/utils/plugin.png"
								alt="TintaDev - Plugin"> <br>
							<br>
							<h5 class="text-center text-muted">R$'.$dado->preco.',00</h5>
							<br>
                            '.form_button("compra", "COMPRAR", array(
                            "name" => "compra",
                            "id" => "button",
                            "value" => "true",
                            "type" => "reset",
                            "content" => "COMPRAR",
                            "class" => "form-control btn btn-primary",
                            "onclick" => "window.location.href='/loja/checkout/" . $dado->nome . "'")).'
						</div>
					</div>
				</div>
				<div class="col-md-8">
					<div class="card package-details">
						<div class="card-block">
							'. $dado->descricao .'
						</div>
					</div>
				</div> ';
                }
            ?>
			</div>
		</div>
	</div>