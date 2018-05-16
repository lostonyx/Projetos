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
				<h3>Criadores / Editar</h3>
			</div>
		</div>
	</header>
	<div class="content-wrapper">
		<div class="blank">
			<div class="row">
			<?php
			foreach ($criadors as $criador){
    			    $arr['id']   = $criador->id;
    			    $_SESSION['editcriador'] = $arr;
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
    				<div class="col-md-9">
    										<div class="bg">
                            <h3>'.show_message().'</h3>
    						<h3>Editar / '.$criador->nome.'</h3>
    						<br>
    							<div class="row">
                                    '.form_open_multipart("/admin/criadores/save/").'
    								<div class="form-group">
									<label>Nome Do Criador: </label> 
                                    <input type="text" value="'.$criador->nome.'" class="form-control" name="name">
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
                                    <input type="text" value="'.$criador->mercadopagoID.'" class="form-control" name="client_id">
                                    <label>client_secret: </label> 
                                    <input type="text" value="'.$criador->mercadopagoSECRET.'" class="form-control" name="client_secret">
								    </div>
                                    <div class="form-group">
                                    <h3>PagSeguro</h3>
									<label>Email: </label> 
                                    <input type="text" value="'.$criador->pagseguroEmail.'" class="form-control" name="email">
                                    <label>Token: </label> 
                                    <input type="text" value="'.$criador->pagseguroToken.'" class="form-control" name="token">
								    </div>
    							</div>
    							<br>
                                <button class="btn btn-success form-control">SALVAR</button>
                                '.form_close().'
    					</div>
    				</div>';	
    			}
    			
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