<?php echo $header; ?><?php echo $column_left; ?>
<div id="content">
<div class="block-header">
    <div class="pull-right">
        <button type="submit" form="form-product" data-toggle="tooltip" title="<?php echo $button_save; ?>" class="btn btn-primary"><i class="fa fa-save"></i></button>
        <a href="<?php echo $cancel; ?>" data-toggle="tooltip" title="<?php echo $button_cancel; ?>" class="btn btn-default"><i class="fa fa-reply"></i></a></div>
    <h2><?php echo $heading_title; ?></h2>
    <ul class="breadcrumb">
        <?php foreach ($breadcrumbs as $breadcrumb) { ?>
        <li><a href="<?php echo $breadcrumb['href']; ?>"><?php echo $breadcrumb['text']; ?></a></li>
        <?php } ?>
    </ul>
</div>
  <div class="container-fluid">
    <?php if ($error_warning) { ?>
    <div class="alert alert-danger"><i class="fa fa-exclamation-circle"></i> <?php echo $error_warning; ?>
      <button type="button" class="close" data-dismiss="alert">&times;</button>
    </div>
    <?php } ?>
    <div class="card">
      <div class="card-header">
        <h3 class="panel-title"><i class="fa fa-pencil"></i> <?php echo $text_form; ?></h3>
      </div>
      <div class="panel-body">
        <form action="<?php echo $action; ?>" method="post" enctype="multipart/form-data" id="form-product" class="form-horizontal">
          <ul class="nav nav-tabs">
            <li class="active"><a href="#tab-general" data-toggle="tab"><?php echo $tab_general; ?></a></li>
            <li><a href="#tab-data" data-toggle="tab"><?php echo $tab_data; ?></a></li>
            <li><a href="#tab-price" data-toggle="tab"><?php echo $tab_price; ?></a></li>
            <li><a href="#tab-links" data-toggle="tab"><?php echo $tab_links; ?></a></li>
            <li><a href="#tab-commands" data-toggle="tab"><?php echo $tab_commands; ?></a></li>
            <li><a href="#tab-recurring" data-toggle="tab"><?php echo $tab_recurring; ?></a></li>
            <!--<li><a href="#tab-discount" data-toggle="tab"><?php echo $tab_discount; ?></a></li>-->
            <li><a href="#tab-special" data-toggle="tab"><?php echo $tab_special; ?></a></li>
          </ul>
          <div class="tab-content">
            <div class="tab-pane active" id="tab-general">
              <ul class="nav nav-tabs" id="language">
                <?php foreach ($languages as $language) { ?>
                <li><a href="#language<?php echo $language['language_id']; ?>" data-toggle="tab"><img src="dashboard/view/image/flags/<?php echo $language['image']; ?>.png" title="<?php echo $language['name']; ?>" /> <?php echo $language['name']; ?></a></li>
                <?php } ?>
              </ul>
              <div class="tab-content">
                <?php foreach ($languages as $language) { ?>
                <div class="tab-pane" id="language<?php echo $language['language_id']; ?>">
                  <div class="form-group required">
                    <label class="col-sm-2 control-label" for="input-name<?php echo $language['language_id']; ?>"><?php echo $entry_name; ?></label>
                    <div class="col-sm-10">
                      <div class="fg-line">
                        <input type="text" name="product_description[<?php echo $language['language_id']; ?>][name]" value="<?php echo isset($product_description[$language['language_id']]) ? $product_description[$language['language_id']]['name'] : ''; ?>" placeholder="<?php echo $entry_name; ?>" id="input-name<?php echo $language['language_id']; ?>" class="form-control" />
                      </div>
                      <?php if (isset($error_name[$language['language_id']])) { ?>
                      <div class="text-danger"><?php echo $error_name[$language['language_id']]; ?></div>
                      <?php } ?>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label" for="input-short-description<?php echo $language['language_id']; ?>"><?php echo $entry_short_description; ?></label>
                    <div class="col-sm-10">
                      <div class="fg-line">
                        <textarea name="product_description[<?php echo $language['language_id']; ?>][short_description]" class="form-control" placeholder="<?php echo $entry_short_description; ?>" id="input-short-description<?php echo $language['language_id']; ?>"><?php echo isset($product_description[$language['language_id']]) ? $product_description[$language['language_id']]['short_description'] : ''; ?></textarea>
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                        <label class="col-sm-2 control-label" for="input-long-description<?php echo $language['language_id']; ?>"><?php echo $entry_long_description; ?></label>
                        <div class="col-sm-10">
                            <div class="fg-line">
                                <textarea name="product_description[<?php echo $language['language_id']; ?>][long_description]" placeholder="<?php echo $entry_long_description; ?>" id="input-long-description<?php echo $language['language_id']; ?>"><?php echo isset($product_description[$language['language_id']]) ? $product_description[$language['language_id']]['long_description'] : ''; ?></textarea>
                            </div>
                        </div>
                  </div>
                </div>
                <?php } ?>
              </div>
            </div>
            <div class="tab-pane" id="tab-data">
              <div class="form-group">
                <label class="col-sm-2 control-label" for="input-image"><?php echo $entry_image; ?><br><small>(200x200)</small></label>
                <div class="col-sm-10">
                  <a href="" id="thumb-image" data-toggle="image" class="img-thumbnail"><img src="<?php echo $thumb; ?>" alt="" title="" data-placeholder="<?php echo $placeholder; ?>" /></a>
                  <input type="hidden" name="image" value="<?php echo $image; ?>" id="input-image" />
                </div>
              </div>
              <div class="form-group required">
                <label class="col-sm-2 control-label" for="input-lifetime"><?php echo $entry_lifetime; ?></label>
                <div class="col-sm-10">
                  <select name="lifetime" id="input-lifetime" class="form-control">
                    <?php if (!$lifetime) { ?>
                    <option value="0" selected="selected"><?php echo $text_yes; ?></option>
                    <option value="1"><?php echo $text_no; ?></option>
                    <?php } else { ?>
                    <option value="0"><?php echo $text_yes; ?></option>
                    <option value="1" selected="selected"><?php echo $text_no; ?></option>
                    <?php } ?>
                  </select>
                </div>
              </div>
              <div class="form-group required">
                <label class="col-sm-2 control-label" for="input-duration"><?php echo $entry_duration; ?></label>
                <div class="col-sm-10">
                  <div class="fg-line"><?php  ?>
                  <input type="text" name="duration" value="<?php echo $duration; ?>" placeholder="<?php echo $entry_duration; ?>" id="input-duration" class="form-control" />
                    </div>
                  <?php if ($error_duration) { ?>
                  <div class="text-danger"><?php echo $error_duration; ?></div>
                  <?php } ?>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label" for="input-date-available"><?php echo $entry_date_available; ?></label>
                <div class="col-sm-3">
                  <div class="input-group date">
                    <div class="fg-line">
                    <input type="text" name="date_available" value="<?php echo $date_available; ?>" placeholder="<?php echo $entry_date_available; ?>" data-date-format="YYYY-MM-DD" id="input-date-available" class="form-control" />
                      </div>
                    <span class="input-group-btn">
                    <button class="btn btn-default" type="button"><i class="fa fa-calendar"></i></button>
                    </span></div>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label" for="input-status"><?php echo $entry_status; ?></label>
                <div class="col-sm-10">
                  <select name="status" id="input-status" class="form-control">
                    <?php if ($status) { ?>
                    <option value="2" selected="selected"><?php echo $text_enabled; ?></option>
                    <option value="0"><?php echo $text_disabled; ?></option>
                    <?php } else { ?>
                    <option value="2"><?php echo $text_enabled; ?></option>
                    <option value="0" selected="selected"><?php echo $text_disabled; ?></option>
                    <?php } ?>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label" for="input-sort-order"><?php echo $entry_sort_order; ?></label>
                <div class="col-sm-10">
                  <div class="fg-line">
                  <input type="text" name="sort_order" value="<?php echo $sort_order; ?>" placeholder="<?php echo $entry_sort_order; ?>" id="input-sort-order" class="form-control" />
                    </div>
                </div>
              </div>
            </div>
            <div class="tab-pane" id="tab-price">
              <?php foreach ($currencies as $currency => $currency_data) { ?>
              <input type="hidden" name="product_price[<?php echo $currency_data['currency_id']; ?>][currency_id]" value="<?=$currency_data['currency_id']?>"/>
              <div class="form-group required">
                <label class="col-sm-2 control-label" for="input-price<?php echo $currency_data['currency_id']; ?>">
                  <span style="float: right; margin-left: 10px;"><?php echo $entry_price; ?>:</span>
                  <h4 style="float: right; margin: 0 auto;"><span class="label label-primary"><?=$currency?></span></h4>
                </label>
                <div class="col-sm-4">
                  <div class="fg-line">
                    <input type="text" name="product_price[<?php echo $currency_data['currency_id']; ?>][price]" value="<?php echo isset($product_price[$currency_data['currency_id']]['price']) ? $product_price[$currency_data['currency_id']]['price'] : ''; ?>" placeholder="<?php echo $entry_price; ?>" id="input-price<?php echo $currency_data['currency_id']; ?>" class="form-control" />
                  </div>
                  <?php if (isset($error_price[$currency_data['currency_id']])) { ?>
                  <div class="text-danger"><?php echo $error_price[$currency_data['currency_id']]; ?></div>
                  <?php } ?>
                </div>
                <?php if ($currency !== $currencycode) { ?>
                <label class="col-sm-2 control-label" for="input-price-convert<?php echo $currency_data['currency_id']; ?>"><?php echo $entry_price_converter; ?>:</label>
                <div class="col-sm-2">
                  <div class="fg-line">
                    <select name="product_price[<?php echo $currency_data['currency_id']; ?>][convert]" id="input-price-convert<?php echo $currency_data['currency_id']; ?>" class="form-control">
                      <?php if (isset($product_price[$currency_data['currency_id']]) && $product_price[$currency_data['currency_id']]['convert']) { ?>
                      <option value="1" selected="selected"><?php echo $text_enabled; ?></option>
                      <option value="0"><?php echo $text_disabled; ?></option>
                      <?php } else { ?>
                      <option value="1"><?php echo $text_enabled; ?></option>
                      <option value="0" selected="selected"><?php echo $text_disabled; ?></option>
                      <?php } ?>
                    </select>
                  </div>
                </div>
                <?php } else { ?>
                <input type="hidden" name="product_price[<?php echo $currency_data['currency_id']; ?>][convert]" value="0">
                <div class="col-sm-4"></div>
                <?php } ?>
                <div class="col-sm-2"></div>
              </div>
              <?php } ?>
            </div>
            <div class="tab-pane" id="tab-links">
              <div class="form-group">
                <label class="col-sm-2 control-label" for="input-category"><span data-toggle="tooltip" title="<?php echo $help_category; ?>"><?php echo $entry_category; ?></span></label>
                <div class="col-sm-10">
                  <div class="fg-line">
                  <input type="text" name="category" value="" placeholder="<?php echo $entry_category; ?>" id="input-category" class="form-control" />
                    </div>
                  <div id="product-category" class="well well-sm" style="height: 150px; overflow: auto;">
                    <?php foreach ($product_categories as $product_category) { ?>
                    <div id="product-category<?php echo $product_category['category_id']; ?>"><i class="fa fa-minus-circle"></i> <?php echo $product_category['name']; ?>
                      <input type="hidden" name="product_category[]" value="<?php echo $product_category['category_id']; ?>" />
                    </div>
                    <?php } ?>
                  </div>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label" for="input-server"><span data-toggle="tooltip" title="<?php echo $help_category; ?>"><?php echo $entry_server; ?></span></label>
                <div class="col-sm-10">
                  <div class="fg-line">
                    <input type="text" name="server" value="" placeholder="<?php echo $entry_server; ?>"
                           id="input-server" class="form-control"/>
                  </div>
                  <div id="product-server" class="well well-sm" style="height: 150px; overflow: auto;">
                    <?php foreach ($product_servers as $product_server) { ?>
                    <div id="product-server<?php echo $product_server['server_id']; ?>"><i class="fa fa-minus-circle"></i> <?php echo $product_server['name']; ?>
                      <input type="hidden" name="product_server[]" value="<?php echo $product_server['server_id']; ?>" />
                    </div>
                    <?php } ?>
                  </div>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label" for="input-minecraft-group"><?php echo $entry_minecraft_group; ?></label>
                <div class="col-sm-10">
                  <div class="fg-line">
                  <select name="minecraft_product_group_id" id="input-minecraft-group" class="form-control">
                    <?php foreach ($minecraft_groups as $group) { ?>
                      <?php if ($group['minecraft_product_group_id'] == $minecraft_product_group_id) { ?>
                    <option value="<?=$group['minecraft_product_group_id']?>" selected><?php echo $group['groupname']; ?></option>
                      <?php } else { ?>
                    <option value="<?=$group['minecraft_product_group_id']?>"><?php echo $group['groupname']; ?></option>
                      <?php } ?>
                    <?php } ?>
                  </select>
                    </div>
                </div>
              </div>
            </div>
            <div class="tab-pane" id="tab-commands">
              <div class="table-responsive">
                <table class="table table-striped table-bordered table-hover">
                  <thead>
                  <tr>
                    <td class="text-left"><?php echo $entry_command_event; ?></td>
                    <td class="text-left"><?php echo $entry_command_value; ?></td>
                    <td class="text-left"></td>
                  </tr>
                  </thead>
                  <tbody>
                  <?php $command_row = 0; ?>
                  <?php foreach ($commands as $command) { ?>

                  <tr id="command-row<?php echo $command_row; ?>">
                    <td class="text-left"><select name="product_command[<?php echo $command_row; ?>][event_id]" class="form-control">
                        <?php foreach ($events as $event) { ?>
                        <?php if ($event['event_id'] == $command['event_id']) { ?>
                        <option value="<?php echo $event['event_id']; ?>" selected="selected"><?php echo $event['name']; ?></option>
                        <?php } else { ?>
                        <option value="<?php echo $event['event_id']; ?>"><?php echo $event['name']; ?></option>
                        <?php } ?>
                        <?php } ?>
                      </select></td>
                    <td class="text-right"><div class="fg-line"><input type="text" name="product_command[<?php echo $command_row; ?>][value]" value="<?php echo $command['value']; ?>" placeholder="<?php echo $entry_command; ?>" class="form-control" /></div></td>
                    <td class="text-left"><button type="button" onclick="$('#command-row<?php echo $command_row; ?>').remove()" data-toggle="tooltip" title="<?php echo $button_remove; ?>" class="btn btn-danger"><i class="fa fa-minus-circle"></i></button></td>
                  </tr>
                  <?php $command_row++; ?>
                  <?php } ?>
                  </tbody>
                  <tfoot>
                  <tr>
                    <td colspan="2"></td>
                    <td class="text-left"><button type="button" onclick="addCommand()" data-toggle="tooltip" title="<?php echo $button_command_add; ?>" class="btn btn-primary"><i class="fa fa-plus-circle"></i></button></td>
                  </tr>
                  </tfoot>
                </table>
              </div>
            </div>
            <div class="tab-pane" id="tab-recurring">
              <div class="table-responsive">
                <table class="table table-striped table-bordered table-hover">
                  <thead>
                    <tr>
                      <td class="text-left"><?php echo $entry_recurring; ?></td>
                      <td class="text-left"><?php echo $entry_customer_group; ?></td>
                      <td class="text-left"></td>
                    </tr>
                  </thead>
                  <tbody>
                    <?php $recurring_row = 0; ?>
                    <?php foreach ($product_recurrings as $product_recurring) { ?>

                    <tr id="recurring-row<?php echo $recurring_row; ?>">
                      <td class="text-left"><select name="product_recurring[<?php echo $recurring_row; ?>][recurring_id]" class="form-control">
                          <?php foreach ($recurrings as $recurring) { ?>
                          <?php if ($recurring['recurring_id'] == $product_recurring['recurring_id']) { ?>
                          <option value="<?php echo $recurring['recurring_id']; ?>" selected="selected"><?php echo $recurring['name']; ?></option>
                          <?php } else { ?>
                          <option value="<?php echo $recurring['recurring_id']; ?>"><?php echo $recurring['name']; ?></option>
                          <?php } ?>
                          <?php } ?>
                        </select></td>
                      <td class="text-left"><select name="product_recurring[<?php echo $recurring_row; ?>][customer_group_id]" class="form-control">
                          <?php foreach ($customer_groups as $customer_group) { ?>
                          <?php if ($customer_group['customer_group_id'] == $product_recurring['customer_group_id']) { ?>
                          <option value="<?php echo $customer_group['customer_group_id']; ?>" selected="selected"><?php echo $customer_group['name']; ?></option>
                          <?php } else { ?>
                          <option value="<?php echo $customer_group['customer_group_id']; ?>"><?php echo $customer_group['name']; ?></option>
                          <?php } ?>
                          <?php } ?>
                        </select></td>
                      <td class="text-left"><button type="button" onclick="$('#recurring-row<?php echo $recurring_row; ?>').remove()" data-toggle="tooltip" title="<?php echo $button_remove; ?>" class="btn btn-danger"><i class="fa fa-minus-circle"></i></button></td>
                    </tr>
                    <?php $recurring_row++; ?>
                    <?php } ?>
                  </tbody>
                  <tfoot>
                    <tr>
                      <td colspan="2"></td>
                      <td class="text-left"><button type="button" onclick="addRecurring()" data-toggle="tooltip" title="<?php echo $button_recurring_add; ?>" class="btn btn-primary"><i class="fa fa-plus-circle"></i></button></td>
                    </tr>
                  </tfoot>
                </table>
              </div>
            </div>
            <div class="tab-pane" id="tab-discount">
              <div class="table-responsive">
                <table id="discount" class="table table-striped table-bordered table-hover">
                  <thead>
                    <tr>
                      <td class="text-left"><?php echo $entry_customer_group; ?></td>
                      <td class="text-right"><?php echo $entry_quantity; ?></td>
                      <td class="text-right"><?php echo $entry_priority; ?></td>
                      <td class="text-right"><?php echo $entry_price; ?></td>
                      <td class="text-left"><?php echo $entry_date_start; ?></td>
                      <td class="text-left"><?php echo $entry_date_end; ?></td>
                      <td></td>
                    </tr>
                  </thead>
                  <tbody>
                    <?php $discount_row = 0; ?>
                    <?php foreach ($product_discounts as $product_discount) { ?>
                    <tr id="discount-row<?php echo $discount_row; ?>">
                      <td class="text-left"><select name="product_discount[<?php echo $discount_row; ?>][customer_group_id]" class="form-control">
                          <?php foreach ($customer_groups as $customer_group) { ?>
                          <?php if ($customer_group['customer_group_id'] == $product_discount['customer_group_id']) { ?>
                          <option value="<?php echo $customer_group['customer_group_id']; ?>" selected="selected"><?php echo $customer_group['name']; ?></option>
                          <?php } else { ?>
                          <option value="<?php echo $customer_group['customer_group_id']; ?>"><?php echo $customer_group['name']; ?></option>
                          <?php } ?>
                          <?php } ?>
                        </select></td>
                      <td class="text-right"><div class="fg-line"><input type="text" name="product_discount[<?php echo $discount_row; ?>][quantity]" value="<?php echo $product_discount['quantity']; ?>" placeholder="<?php echo $entry_quantity; ?>" class="form-control" /></div></td>
                      <td class="text-right"><div class="fg-line"><input type="text" name="product_discount[<?php echo $discount_row; ?>][priority]" value="<?php echo $product_discount['priority']; ?>" placeholder="<?php echo $entry_priority; ?>" class="form-control" /></div></td>
                      <td class="text-right"><div class="fg-line"><input type="text" name="product_discount[<?php echo $discount_row; ?>][price]" value="<?php echo $product_discount['price']; ?>" placeholder="<?php echo $entry_price; ?>" class="form-control" /></div></td>
                      <td class="text-left" style="width: 20%;"><div class="input-group date">
                          <div class="fg-line">
                          <input type="text" name="product_discount[<?php echo $discount_row; ?>][date_start]" value="<?php echo $product_discount['date_start']; ?>" placeholder="<?php echo $entry_date_start; ?>" data-date-format="YYYY-MM-DD" class="form-control" />
                            </div>
                          <span class="input-group-btn">
                          <button class="btn btn-default" type="button"><i class="fa fa-calendar"></i></button>
                          </span></div></td>
                      <td class="text-left" style="width: 20%;"><div class="input-group date">
                          <div class="fg-line">
                          <input type="text" name="product_discount[<?php echo $discount_row; ?>][date_end]" value="<?php echo $product_discount['date_end']; ?>" placeholder="<?php echo $entry_date_end; ?>" data-date-format="YYYY-MM-DD" class="form-control" />
                            </div>
                          <span class="input-group-btn">
                          <button class="btn btn-default" type="button"><i class="fa fa-calendar"></i></button>
                          </span></div></td>
                      <td class="text-left"><button type="button" onclick="$('#discount-row<?php echo $discount_row; ?>').remove();" data-toggle="tooltip" title="<?php echo $button_remove; ?>" class="btn btn-danger"><i class="fa fa-minus-circle"></i></button></td>
                    </tr>
                    <?php $discount_row++; ?>
                    <?php } ?>
                  </tbody>
                  <tfoot>
                    <tr>
                      <td colspan="6"></td>
                      <td class="text-left"><button type="button" onclick="addDiscount();" data-toggle="tooltip" title="<?php echo $button_discount_add; ?>" class="btn btn-primary"><i class="fa fa-plus-circle"></i></button></td>
                    </tr>
                  </tfoot>
                </table>
              </div>
            </div>
            <div class="tab-pane" id="tab-special">
              <div class="table-responsive">
                <table id="special" class="table table-striped table-bordered table-hover">
                  <thead>
                  <tr>
                    <td class="text-left"><?php echo $entry_customer_group; ?></td>
                    <td class="text-right"><?php echo $entry_priority; ?></td>
                    <td class="text-right"><?php echo $entry_price; ?></td>
                    <td class="text-left"><?php echo $entry_date_start; ?></td>
                    <td class="text-left"><?php echo $entry_date_end; ?></td>
                    <td></td>
                  </tr>
                  </thead>
                  <tbody>
                  <?php $special_row = 0; ?>
                  <?php foreach ($product_specials as $product_special) { ?>
                  <tr id="special-row<?php echo $special_row; ?>">
                    <td class="text-left"><select name="product_special[<?php echo $special_row; ?>][customer_group_id]"
                                                  class="form-control">
                        <?php foreach ($customer_groups as $customer_group) { ?>
                        <?php if ($customer_group['customer_group_id'] == $product_special['customer_group_id']) { ?>
                        <option value="<?php echo $customer_group['customer_group_id']; ?>"
                                selected="selected"><?php echo $customer_group['name']; ?></option>
                        <?php } else { ?>
                        <option value="<?php echo $customer_group['customer_group_id']; ?>"><?php echo $customer_group['name']; ?></option>
                        <?php } ?>
                        <?php } ?>
                      </select></td>
                    <td class="text-right">
                      <div class="fg-line"><input type="text"
                                                  name="product_special[<?php echo $special_row; ?>][priority]"
                                                  value="<?php echo $product_special['priority']; ?>"
                                                  placeholder="<?php echo $entry_priority; ?>" class="form-control"/>
                      </div>
                    </td>
                    <td class="text-right">
                      <div class="fg-line"><input type="text" name="product_special[<?php echo $special_row; ?>][price]"
                                                  value="<?php echo $product_special['price']; ?>"
                                                  placeholder="<?php echo $entry_price; ?>" class="form-control"/></div>
                    </td>
                    <td class="text-left" style="width: 20%;">
                      <div class="input-group date">
                        <div class="fg-line">
                          <input type="text" name="product_special[<?php echo $special_row; ?>][date_start]"
                                 value="<?php echo $product_special['date_start']; ?>"
                                 placeholder="<?php echo $entry_date_start; ?>" data-date-format="YYYY-MM-DD"
                                 class="form-control"/></div>
                          <span class="input-group-btn">
                          <button class="btn btn-default" type="button"><i class="fa fa-calendar"></i></button>
                          </span>
                        </div>
                    </td>
                    <td class="text-left" style="width: 20%;">
                      <div class="input-group date">
                        <div class="fg-line">
                          <input type="text" name="product_special[<?php echo $special_row; ?>][date_end]"
                                 value="<?php echo $product_special['date_end']; ?>"
                                 placeholder="<?php echo $entry_date_end; ?>" data-date-format="YYYY-MM-DD"
                                 class="form-control"/>
                        </div>
                          <span class="input-group-btn">
                          <button class="btn btn-default" type="button"><i class="fa fa-calendar"></i></button>
                          </span></div>
                    </td>
                    <td class="text-left">
                      <button type="button" onclick="$('#special-row<?php echo $special_row; ?>').remove();"
                              data-toggle="tooltip" title="<?php echo $button_remove; ?>" class="btn btn-danger"><i
                                class="fa fa-minus-circle"></i></button>
                    </td>
                  </tr>
                  <?php $special_row++; ?>
                  <?php } ?>
                  </tbody>
                  <tfoot>
                  <tr>
                    <td colspan="5"></td>
                    <td class="text-left">
                      <button type="button" onclick="addSpecial();" data-toggle="tooltip"
                              title="<?php echo $button_special_add; ?>" class="btn btn-primary"><i
                                class="fa fa-plus-circle"></i></button>
                    </td>
                  </tr>
                  </tfoot>
                </table>
              </div>
            </div>
        </form>
      </div>
    </div>
  </div>
  <script type="text/javascript"><!--
<?php foreach ($languages as $language) { ?>
$('#input-long-description<?php echo $language['language_id']; ?>').summernote({height: 300});
<?php } ?>
//--></script>
  <script type="text/javascript"><!--

// Currency
  $(document).ready(function(){

      <?php foreach ($currencies as $currency => $currency_data) { ?>

      // GENERATED AUTOMATICALLY by IW4 Store Currency Module
      $('#input-price-convert<?=$currency_data['currency_id']?>').change(function() {
        if ($(this).val() == '1') {
          $('#input-price<?=$currency_data['currency_id']?>').attr("disabled", "");
        } else {
          $('#input-price<?=$currency_data['currency_id']?>').removeAttr("disabled");
        }
      });

      if ($('#input-price-convert<?=$currency_data['currency_id']?>').val() == '1') {
        $('#input-price<?=$currency_data['currency_id']?>').attr("disabled", "");
      }
      // ---
      <?php } ?>

  });

// Category
$('input[name=\'category\']').autocomplete({
	'source': function(request, response) {
		$.ajax({
			url: 'dashboard/catalog/category/autocomplete?token=<?php echo $token; ?>&filter_name=' +  encodeURIComponent(request),
			dataType: 'json',
			success: function(json) {
				response($.map(json, function(item) {
					return {
						label: item['name'],
						value: item['category_id']
					}
				}));
			}
		});
	},
	'select': function(item) {
		$('input[name=\'category\']').val('');

		$('#product-category' + item['value']).remove();

		$('#product-category').append('<div id="product-category' + item['value'] + '"><i class="fa fa-minus-circle"></i> ' + item['label'] + '<input type="hidden" name="product_category[]" value="' + item['value'] + '" /></div>');
	}
});

$('#product-category').delegate('.fa-minus-circle', 'click', function() {
	$(this).parent().remove();
});

// Server
$('input[name=\'server\']').autocomplete({
  'source': function(request, response) {
    $.ajax({
      url: 'dashboard/catalog/server/autocomplete?token=<?php echo $token; ?>&filter_name=' +  encodeURIComponent(request),
      dataType: 'json',
      success: function(json) {
        response($.map(json, function(item) {
          return {
            label: item['name'],
            value: item['server_id']
          }
        }));
      }
    });
  },
  'select': function(item) {
    $('input[name=\'server\']').val('');

    $('#product-server' + item['value']).remove();

    $('#product-server').append('<div id="product-server' + item['value'] + '"><i class="fa fa-minus-circle"></i> ' + item['label'] + '<input type="hidden" name="product_server[]" value="' + item['value'] + '" /></div>');
  }
});

$('#product-server').delegate('.fa-minus-circle', 'click', function() {
  $(this).parent().remove();
});
// Commands
//--></script>
<script type="text/javascript"><!--
  var command_row = <?php echo $command_row; ?>;

  function addCommand() {
    command_row++;

    html  = '';
    html += '<tr id="command-row' + command_row + '">';
    html += '  <td class="left">';
    html += '    <select name="product_command[' + command_row + '][event_id]" class="form-control">>';
  <?php foreach ($events as $event) { ?>
      html += '      <option value="<?php echo $event['event_id']; ?>"><?php echo $event['name']; ?></option>';
    <?php } ?>
    html += '    </select>';
    html += '  </td>';
    html += '  <td class="text-right"><div class="fg-line"><input type="text" name="product_command[' + command_row + '][value]" value="" placeholder="<?php echo $entry_command; ?>" class="form-control" /></div></td>';
    html += '  <td class="left">';
    html += '    <a onclick="$(\'#command-row' + command_row + '\').remove()" data-toggle="tooltip" title="<?php echo $button_remove; ?>" class="btn btn-danger"><i class="fa fa-minus-circle"></i></a>';
    html += '  </td>';
    html += '</tr>';

    $('#tab-commands table tbody').append(html);
  }
  //--></script>
  <script type="text/javascript"><!--
var discount_row = <?php echo $discount_row; ?>;

function addDiscount() {
	html  = '<tr id="discount-row' + discount_row + '">';
    html += '  <td class="text-left"><select name="product_discount[' + discount_row + '][customer_group_id]" class="form-control">';
    <?php foreach ($customer_groups as $customer_group) { ?>
    html += '    <option value="<?php echo $customer_group['customer_group_id']; ?>"><?php echo addslashes($customer_group['name']); ?></option>';
    <?php } ?>
    html += '  </select></td>';
    html += '  <td class="text-right"><div class="fg-line"><input type="text" name="product_discount[' + discount_row + '][quantity]" value="" placeholder="<?php echo $entry_quantity; ?>" class="form-control" /></div></td>';
    html += '  <td class="text-right"><div class="fg-line"><input type="text" name="product_discount[' + discount_row + '][priority]" value="" placeholder="<?php echo $entry_priority; ?>" class="form-control" /></div></td>';
	html += '  <td class="text-right"><div class="fg-line"><input type="text" name="product_discount[' + discount_row + '][price]" value="" placeholder="<?php echo $entry_price; ?>" class="form-control" /></div></td>';
    html += '  <td class="text-left" style="width: 20%;"><div class="input-group date"><div class="fg-line"><input type="text" name="product_discount[' + discount_row + '][date_start]" value="" placeholder="<?php echo $entry_date_start; ?>" data-date-format="YYYY-MM-DD" class="form-control" /></div><span class="input-group-btn"><button type="button" class="btn btn-default"><i class="fa fa-calendar"></i></button></span></div></td>';
	html += '  <td class="text-left" style="width: 20%;"><div class="input-group date"><div class="fg-line"><input type="text" name="product_discount[' + discount_row + '][date_end]" value="" placeholder="<?php echo $entry_date_end; ?>" data-date-format="YYYY-MM-DD" class="form-control" /></div><span class="input-group-btn"><button type="button" class="btn btn-default"><i class="fa fa-calendar"></i></button></span></div></td>';
	html += '  <td class="text-left"><button type="button" onclick="$(\'#discount-row' + discount_row + '\').remove();" data-toggle="tooltip" title="<?php echo $button_remove; ?>" class="btn btn-danger"><i class="fa fa-minus-circle"></i></button></td>';
	html += '</tr>';

	$('#discount tbody').append(html);

	$('.date').datetimepicker({
		pickTime: false
	});

	discount_row++;
}
//--></script>
  <script type="text/javascript"><!--
var special_row = <?php echo $special_row; ?>;

function addSpecial() {
	html  = '<tr id="special-row' + special_row + '">';
    html += '  <td class="text-left"><select name="product_special[' + special_row + '][customer_group_id]" class="form-control">';
    <?php foreach ($customer_groups as $customer_group) { ?>
    html += '      <option value="<?php echo $customer_group['customer_group_id']; ?>"><?php echo addslashes($customer_group['name']); ?></option>';
    <?php } ?>
    html += '  </select></td>';
    html += '  <td class="text-right"><div class="fg-line"><input type="text" name="product_special[' + special_row + '][priority]" value="" placeholder="<?php echo $entry_priority; ?>" class="form-control" /></div></td>';
	html += '  <td class="text-right"><div class="fg-line"><input type="text" name="product_special[' + special_row + '][price]" value="" placeholder="<?php echo $entry_price; ?>" class="form-control" /></div></td>';
    html += '  <td class="text-left" style="width: 20%;"><div class="input-group date"><div class="fg-line"><input type="text" name="product_special[' + special_row + '][date_start]" value="" placeholder="<?php echo $entry_date_start; ?>" data-date-format="YYYY-MM-DD" class="form-control" /></div><span class="input-group-btn"><button type="button" class="btn btn-default"><i class="fa fa-calendar"></i></button></span></div></td>';
	html += '  <td class="text-left" style="width: 20%;"><div class="input-group date"><div class="fg-line"><input type="text" name="product_special[' + special_row + '][date_end]" value="" placeholder="<?php echo $entry_date_end; ?>" data-date-format="YYYY-MM-DD" class="form-control" /></div><span class="input-group-btn"><button type="button" class="btn btn-default"><i class="fa fa-calendar"></i></button></span></div></td>';
	html += '  <td class="text-left"><button type="button" onclick="$(\'#special-row' + special_row + '\').remove();" data-toggle="tooltip" title="<?php echo $button_remove; ?>" class="btn btn-danger"><i class="fa fa-minus-circle"></i></button></td>';
	html += '</tr>';

	$('#special tbody').append(html);

	$('.date').datetimepicker({
		pickTime: false
	});

	special_row++;
}
//--></script>
  <script type="text/javascript"><!--
var recurring_row = <?php echo $recurring_row; ?>;

function addRecurring() {
	recurring_row++;

	html  = '';
	html += '<tr id="recurring-row' + recurring_row + '">';
	html += '  <td class="left">';
	html += '    <select name="product_recurring[' + recurring_row + '][recurring_id]" class="form-control">>';
	<?php foreach ($recurrings as $recurring) { ?>
	html += '      <option value="<?php echo $recurring['recurring_id']; ?>"><?php echo $recurring['name']; ?></option>';
	<?php } ?>
	html += '    </select>';
	html += '  </td>';
	html += '  <td class="left">';
	html += '    <select name="product_recurring[' + recurring_row + '][customer_group_id]" class="form-control">>';
	<?php foreach ($customer_groups as $customer_group) { ?>
	html += '      <option value="<?php echo $customer_group['customer_group_id']; ?>"><?php echo $customer_group['name']; ?></option>';
	<?php } ?>
	html += '    <select>';
	html += '  </td>';
	html += '  <td class="left">';
	html += '    <a onclick="$(\'#recurring-row' + recurring_row + '\').remove()" data-toggle="tooltip" title="<?php echo $button_remove; ?>" class="btn btn-danger"><i class="fa fa-minus-circle"></i></a>';
	html += '  </td>';
	html += '</tr>';

	$('#tab-recurring table tbody').append(html);
}
//--></script>
  <script type="text/javascript"><!--
$('.date').datetimepicker({
	pickTime: false
});

$('.time').datetimepicker({
	pickDate: false
});

$('.datetime').datetimepicker({
	pickDate: true,
	pickTime: true
});
//--></script>
  <script type="text/javascript"><!--
$('#language a:first').tab('show');
$('#option a:first').tab('show');
//--></script></div>
<?php echo $footer; ?>
