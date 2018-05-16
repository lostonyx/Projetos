<?php echo $header; ?><?php echo $column_left; ?>
<div id="content">
	<div class="block-header">
		<div class="pull-right">
			<button type="submit" form="form-lcsip" data-toggle="tooltip" title="<?php echo $button_save; ?>" class="btn btn-primary"><i class="fa fa-save"></i></button>
			<a href="<?php echo $cancel; ?>" data-toggle="tooltip" title="<?php echo $button_cancel; ?>" class="btn btn-default"><i class="fa fa-reply"></i></a>
		</div>
		<h2><?php echo $heading_title; ?></h2>
		<ul class="breadcrumb">
			<?php foreach ($breadcrumbs as $breadcrumb) { ?>
			<li><a href="<?php echo $breadcrumb['href']; ?>"><?php echo $breadcrumb['text']; ?></a></li>
			<?php } ?>
		</ul>
	</div>
  <div class="container-fluid">
    <?php if ($update) { ?>
    <div class="alert alert-info"><i class="fa fa-information-circle"></i> <?php echo $update; ?>
      <button type="button" class="close" data-dismiss="alert">&times;</button>
    </div>
    <?php } ?>  
    <?php if ($error_warning) { ?>
    <div class="alert alert-danger"><i class="fa fa-exclamation-circle"></i> <?php echo $error_warning; ?>
      <button type="button" class="close" data-dismiss="alert">&times;</button>
    </div>
    <?php } ?>
    <div class="card">
      <div class="card-header">
        <h3 class="panel-title"><i class="fa fa-pencil"></i> <?php echo $text_edit; ?></h3>
      </div>
      <div class="panel-body">
			<ul class="nav nav-tabs" id="tabs">
				<li class="active"><a href="#tab-setting" data-toggle="tab"><i class="fa fa-fw fa-wrench"></i> <?php echo $tab_setting; ?></a></li>
			</ul>

			<div class="tab-content">
				<div class="tab-pane active" id="tab-setting">  
					<form action="<?php echo $action; ?>" method="post" enctype="multipart/form-data" id="form-lcsip" class="form-horizontal">
						<?php if (!$clc_relations_total) { ?>
						<div class="alert alert-info"><i class="fa fa-fw fa-info"></i> <?php echo $help_add_relation; ?></div>
						<?php } ?> 
						<div class="form-group">
							<div class="col-sm-12">	
								<table id="clc_relation" class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<td class="text-left required"><?php echo $entry_country; ?></td>
											<td class="text-left required"><?php echo $entry_language; ?></td>
											<td class="text-left required"><?php echo $entry_currency; ?></td>
											<td></td>
										</tr>
									</thead>
									<tbody>
										<?php $lcsip_row = 0; ?>
										<?php foreach ($clc_relations as $clc_relation) { ?>
										<tr id="lcsip-row<?php echo $lcsip_row; ?>">
											<td class="text-left">
												<div class="input-group">
													<span class="input-group-addon"><i class="fa fa-flag-o"></i></span>
													<select name="lc_switch_ip_based[<?php echo $lcsip_row; ?>][country_code]" class="form-control">
														<?php foreach ($countries as $country) { ?>
													    <?php if (strtolower($country['iso_code_2']) == $clc_relation['country_code']) { ?>
													    <option value="<?php echo strtolower($country['iso_code_2']); ?>" selected="selected"><?php echo $country['name'] . ' (' . $country['iso_code_2'] . ')'; ?></option>
													    <?php } else { ?>
													    <option value="<?php echo strtolower($country['iso_code_2']); ?>"><?php echo $country['name'] . ' (' . $country['iso_code_2'] . ')'; ?></option>
													    <?php } ?>
													    <?php } ?>
													</select>
												</div>										
											</td>	
											<td class="text-left">
												<div class="input-group">
													<span class="input-group-addon"><i class="fa fa-language"></i></span>
													<select name="lc_switch_ip_based[<?php echo $lcsip_row; ?>][language_code]" class="form-control">
														<?php foreach ($languages as $language) { ?>
														<?php if ($language['code'] == $clc_relation['language_code']) { ?>
														<option value="<?php echo $language['code']; ?>" selected="selected"><?php echo $language['name'] . ' (' . $language['code'] . ')'; ?></option>
														<?php } else { ?>
														<option value="<?php echo $language['code']; ?>"><?php echo $language['name'] . ' (' . $language['code'] . ')'; ?></option>
														<?php } ?>
														<?php } ?>
													</select>
												</div>										
											</td>
											<td class="text-left">
												<div class="input-group">
													<span class="input-group-addon"><i class="fa fa-money"></i></span>
													<select name="lc_switch_ip_based[<?php echo $lcsip_row; ?>][currency_code]" class="form-control">
														<?php foreach ($currencies as $currency) { ?>
														<?php if ($currency['code'] == $clc_relation['currency_code']) { ?>
														<option value="<?php echo $currency['code']; ?>" selected="selected"><?php echo $currency['title'] . ' (' . $currency['code'] . ')'; ?></option>
														<?php } else { ?>
														<option value="<?php echo $currency['code']; ?>"><?php echo $currency['title'] . ' (' . $currency['code'] . ')'; ?></option>
														<?php } ?>
														<?php } ?>
													</select>
												</div>										
											</td>											
											<td class="text-left"><button type="button" onclick="$('#lcsip-row<?php echo $lcsip_row; ?>').remove();" data-toggle="tooltip" title="<?php echo $button_remove; ?>" class="btn btn-danger"><i class="fa fa-minus-circle"></i></button></td>
										</tr>
										<?php $lcsip_row++; ?>
										<?php } ?>
									</tbody>
									<tfoot>
										<tr>
											<td colspan="3"></td>
											<td class="text-left"><button type="button" onclick="addCountryLanguageCurrencyRelation();" data-toggle="tooltip" title="<?php echo $button_add_relation; ?>" class="btn btn-primary"><i class="fa fa-plus-circle"></i></button></td>
										</tr>
									</tfoot>
								</table>
							</div>	
						</div>
					</form>	
				</div>

			</div>
    </div>
  </div>
<script type="text/javascript"><!--
var lcsip_row = <?php echo $lcsip_row; ?>;

function addCountryLanguageCurrencyRelation() {
	html  = '<tr id="lcsip-row' + lcsip_row + '">';
	html += '  <td class="text-left">';
	html += ' 	   <div class="input-group">';
	html += '          <span class="input-group-addon"><i class="fa fa-flag-o"></i></span>';
	html += '          <select name="lc_switch_ip_based[' + lcsip_row + '][country_code]" class="form-control">';
							<?php foreach ($countries as $country) { ?>
	html += '           	<option value="<?php echo strtolower($country['iso_code_2']); ?>"><?php echo addslashes($country['name']); ?></option>';					
							<?php } ?>
	html += '          </select>';
	html += '      </div>';
	html += '  </td>';
	html += '  <td class="text-left">';
	html += ' 	   <div class="input-group">';
	html += '          <span class="input-group-addon"><i class="fa fa-language"></i></span>';
	html += '          <select name="lc_switch_ip_based[' + lcsip_row + '][language_code]" class="form-control">';
							<?php foreach ($languages as $language) { ?>
	html += '           	<option value="<?php echo $language['code']; ?>"><?php echo addslashes($language['name'] . ' (' . $language['code'] . ')'); ?></option>';					
							<?php } ?>
	html += '          </select>';
	html += '      </div>';
	html += '  </td>';
	html += '  <td class="text-left">';
	html += ' 	   <div class="input-group">';
	html += '          <span class="input-group-addon"><i class="fa fa-money"></i></span>';
	html += '          <select name="lc_switch_ip_based[' + lcsip_row + '][currency_code]" class="form-control">';
							<?php foreach ($currencies as $currency) { ?>
	html += '           	<option value="<?php echo $currency['code']; ?>"><?php echo addslashes($currency['title'] . ' (' . $currency['code'] . ')'); ?></option>';					
							<?php } ?>
	html += '          </select>';
	html += '      </div>';
	html += '  </td>';	
	html += '  <td class="text-left"><button type="button" onclick="$(\'#lcsip-row' + lcsip_row + '\').remove();" class="btn btn-danger"><i class="fa fa-minus-circle"></i></button></td>';
	html += '</tr>';
	
	$('#clc_relation tbody').append(html);  
	
	lcsip_row++;
}
//--></script></div>
<?php echo $footer; ?>