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
				<h3>Plugins / Editar</h3>
			</div>
		</div>
	</header>
	<div class="content-wrapper">
		<div class="blank">
			<div class="row">
			<?php
			$CI =& get_instance();
			$CI->load->model('plugin_model');
    			    echo '
    				<div class="col-md-9">
    										<div class="bg">
    						<h3>Criar um plugin</h3>
    						<br>
    							<div class="row">
                                    '.form_open_multipart("/admin/plugins/create/").'
    								<div class="form-group">
									<label>Nome Do Plugin: </label> 
                                    <input type="text" value="Nome do seu Plugin" class="form-control" name="name">
								    </div>
                                    <div class="form-group">
									<label>Tipo: </label> 
                                    <select name="tipo" class="form-control">
                                      <option class="form-control" value="0">ReflectionClass</option>
                                      <option class="form-control" value="1">OneClass</option>
                                    </select>
								    </div>
                                    <div class="form-group">
									<label>Pago: </label> 
                                    <select name="pago" class="form-control">
                                      <option class="form-control" value="0">Sim</option>
                                      <option class="form-control" value="1">Nao</option>
                                    </select>
								    </div>
                                    <div class="form-group">
									<label>Criador: </label> 
                                    <select name="criador" class="form-control">';
                                      $resu = $CI->plugin_model->getCriadorAll();
                                      foreach($resu as $criadores){
                                          echo '<option class="form-control" value="'.$criadores->id.'">'.$criadores->nome.'</option>';
    			                      } 
                                      echo '
                                    </select>
								    </div>
                                    <div class="form-group">
									<label>Jar do Plugin: </label> 
                                    <input type="file" name="pluginjar">
								    </div>
                                    <div class="form-group">
									<label>Preco: </label> 
                                    <input type="text" value="Preco equivalente" class="form-control" name="preco">
								    </div>
                                    <div>
                                    <td align="right" valign="top" nowrap="nowrap">
                                    <label>Descricao: </label>
                                    <textarea id="descricao" name="descricao" rows="11" style="width:650px"></textarea><script>
                        			CKEDITOR.replace( "descricao", {
                        				fullPage: true,
                        				allowedContent: true,
                        				extraPlugins: "wysiwygarea"
                        			});
		                            </script></td>
                                    </div>
    							</div>
    							<br>
                                <button class="btn btn-success form-control">SALVAR</button>
                                '.form_close().'
                                '.form_open('/admin/plugins/').'
                                <button class="btn btn-success form-control">CANCELAR</button>
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