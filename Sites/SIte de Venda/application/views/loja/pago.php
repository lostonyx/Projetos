
<header class="intro">
	<div class="mid">
		<div class="container"></div>
	</div>
</header>
<div class="content-wrapper">
	<div class="blank">
		<h4 class="no-margin">Loja - Pago</h4>
	</div>
	<div class="bg">
		<div class="row">
			<div class="col-md-3">
				<div class="selector">
					<div class="title">
						<i class="ion-funnel"></i> Filtrar por categoria
					</div>
					<ul>
						<li class="active"><a href="#">Todos</a></li>
						<li><a href="#">Factions</a></li>
						<li><a href="#">FullPvP</a></li>
						<li><a href="#">Survival</a></li>
						<li><a href="#">RankUP</a></li>
						<li><a href="#">Utilidades</a></li>
						<li><a href="#">Diversão</a></li>
						<li><a href="#">Mecânica</a></li>
						<li><a href="#">Administração</a></li>
					</ul>
				</div>
			</div>
			<div class="col-md-9">
				<div class='row'>
				<?php
    foreach ($plugins as $plugin) {
        echo '<div class="col-md-3">
                        <div class="plugin-1">
                        	<img class="img-fluid" src="' . asset_url() . 'images/utils/plugin.png">
                        	<div class="title">' . $plugin->nome . '</div>
                        	<div class="price">' . $plugin->preco . '</div>
                            ' . form_button('compra', 'COMPRAR', array(
            'name' => 'compra',
            'id' => 'button',
            'value' => 'true',
            'type' => 'reset',
            'content' => 'COMPRAR',
            'class' => 'waves',
            "onclick" => "window.location.href='/loja/checkout/" . $plugin->nome . "'"
        )) . '
                            ' . form_button('detalhe', 'DETALHES', array(
            'name' => 'detalhe',
            'id' => 'button',
            'value' => 'true',
            'type' => 'reset',
            'content' => 'DETALHES',
            'class' => 'waves',
            "onclick" => "window.location.href='/loja/detalhes/" . $plugin->nome . "'"
        )) . '
                		</div>
                	</div>';
    }
    ?>
				</div>
				<nav>
					<?php echo $pagination; ?>
				</nav>
			</div>
		</div>
	</div>
</div>