<?php if (!isset($redirect)) { ?>
<form class="form-horizontal">
    <div class="form-group">
        <div class="col-sm-12">
            <div id="terms">
                &copy; MCPixel - 2017
                <br><br>
                <b>TERMOS DE PAGAMENTO E USO</b>
                <br>
                <ul>
                    <li>Todas as transações são realizadas com operadoras de crédito em certificação adequada, incluindo criptografia SSL moderna.</li>
                    <li>Você terá acesso à fatura, ao rastreamento, estado de sua compra e demais informações através <a href="http://loja.mcpixel.com.br/account/order" target="_blank">deste link</a>.</li>
                </ul>
                <br>
                <b>PRAZO DE ENTREGA DO PRODUTO</b>
                <br>
                <ul>
                    <li>Fique atento aos prazos de aprovação de sua compra, eles são variáveis dependendo do método de pagamento escolhido.</li>
                    <li>Transações por Cartão de crédito ou Créditos na loja são liberadas automaticamente, requisitando apenas o login no jogo para ativação.</li>
                    <li>Transações por Depósito ou Boleto (PagSeguro), podem demorar até 3 dias úteis pós-pagamento da fatura.</li>
                </ul>
				<br>
                <b>SAC E ATENDIMENTO</b>
                <br>
				<ul>
					<li>Entre em contato através do e-mail <a href="mailto:vendas@mcpixel.com.br">vendas@mcpixel.com.br</a> para qualquer dúvida sobre sua transação. Você deverá ter retorno em até 48 horas, dias úteis.</li>
				 </ul>
                <br>
            </div>
        </div>
    </div>
</form>
<?=$payment?>
<?php } else { ?>
<script type="text/javascript"><!--
location = '<?php echo $redirect; ?>';
//--></script>
<?php } ?>
