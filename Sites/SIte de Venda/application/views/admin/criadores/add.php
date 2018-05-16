<?php
defined('BASEPATH') or exit('No direct script access allowed');
header('Content-Type: text/html; charset=iso-8859-1');
?>
<html>
<head>
<title>TintaDev - Cliente</title>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="og:title" content="TintaDev - Cliente">
<meta name="og:site_name" content="StringMC">
<meta name="og:type" content="website">
<meta name="robots" CONTENT="index, follow">
<meta name="author"
	content="M4CH4D0_ ( @jm4ch4d0 ou
+55 32 98413-9225 )">
<meta name="theme-color" content="#2E2E2E">
<link rel='shortcut icon' type='image/x-icon'
	href='<?php echo asset_url();?>images/logo-icon.png' />
<link href="<?php echo asset_url();?>css/normalize/normalize.min.css"
	rel="stylesheet" type="text/css">
<link href="<?php echo asset_url();?>css/ionicons/ionicons.min.css"
	rel="stylesheet" type="text/css">
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="<?php echo asset_url();?>css/bootstrap/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="<?php echo asset_url();?>css/waves/waves.min.css"
	rel="stylesheet" type="text/css">
<link href="<?php echo asset_url();?>css/animate/animate.css"
	rel="stylesheet" type="text/css">
<link href="<?php echo asset_url();?>css/core.min.css" rel="stylesheet"
	type="text/css">
<script src="<?php echo asset_url();?>scripts/ckeditor/ckeditor.js"></script>
<script src="<?php echo asset_url();?>scripts/ckeditor/sample.js"></script>
<link rel="stylesheet" href="<?php echo asset_url();?>scripts/ckeditor/samples/sample.css">
<script src="<?php echo asset_url();?>scripts/tiny_mce/tiny_mce.js" type="text/javascript"></script>
</head>
<body>
	<header class="intro">
		<div class="mid">
			<div class="container">
				<h3>Criadores / Criar</h3>
			</div>
		</div>
	</header>
	<div class="content-wrapper">
		<div class="blank">
			<div class="row">
			<?php
    			    echo '
    				<div class="col-md-9">
    										<div class="bg">
                            <h3>'.show_message().'</h3>
    						<h3>Criar</h3>
    						<br>
    							<div class="row">
                                    '.form_open_multipart("/admin/criadores/create/").'
    								<div class="form-group">
									<label>Nome Do Criador: </label> 
                                    <input type="text" class="form-control" name="name">
								    </div>
                                    <div class="form-group">
									<label>MercadoPago: </label> 
                                    <select name="mercadopago" class="form-control">
                                      <option class="form-control" value="0">Desligado</option>
                                      <option class="form-control" value="1">Ligado</option>
                                    </select>
								    </div>
                                    <div class="form-group">
									<label>PagSeguro: </label> 
                                    <select name="pagseguro" class="form-control">
                                      <option class="form-control" value="0">Desligado</option>
                                      <option class="form-control" value="1">Ligado</option>
                                    </select>
								    </div>
                                    <div class="form-group">
                                    <h3>MercadoPago</h3>
									<label>client_id: </label> 
                                    <input type="text" class="form-control" name="client_id">
                                    <label>client_secret: </label> 
                                    <input type="text" class="form-control" name="client_secret">
								    </div>
                                    <div class="form-group">
                                    <h3>PagSeguro</h3>
									<label>Email: </label> 
                                    <input type="text" class="form-control" name="email">
                                    <label>Token: </label> 
                                    <input type="text" class="form-control" name="token">
								    </div>
    							</div>
    							<br>
                                <button class="btn btn-success form-control">SALVAR</button>
                                '.form_close().'
    					</div>
    				</div>';	
			?>
			</div>
		</div>
	</div>
	<script src="<?php echo asset_url();?>js/jquery/jquery.min.js"></script>
	<script src="<?php echo asset_url();?>js/popper/umd/popper.min.js"></script>
	<script src="<?php echo asset_url();?>js/bootstrap/bootstrap.min.js"></script>
	<script src="<?php echo asset_url();?>js/waves/waves.min.js"></script>
	<script src="<?php echo asset_url();?>js/wow/wow.min.js"></script>
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
	<script src="//cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
	<script
		src="//cdn.datatables.net/1.10.16/js/dataTables.bootstrap4.min.js"></script>
	<script src="<?php echo asset_url();?>js/init.min.js"></script>
</body>
</html>