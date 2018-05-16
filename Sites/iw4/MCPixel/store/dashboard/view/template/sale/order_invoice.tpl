<!DOCTYPE html>
<html dir="<?php echo $direction; ?>" lang="<?php echo $lang; ?>">
<head>
<meta charset="UTF-8" />
<title><?php echo $title; ?></title>
<base href="<?php echo $base; ?>" />
<link href="dashboard/view/javascript/bootstrap/css/bootstrap.css" rel="stylesheet" media="all" />
<script type="text/javascript" src="dashboard/view/javascript/jquery/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="dashboard/view/javascript/bootstrap/js/bootstrap.min.js"></script>
<link href="dashboard/view/javascript/font-awesome/css/font-awesome.min.css" type="text/css" rel="stylesheet" />
<link type="text/css" href="dashboard/view/stylesheet/stylesheet.css" rel="stylesheet" media="all" />
</head>
<body>
<div class="container">
  <?php foreach ($orders as $order) { ?>
  <div style="page-break-after: always;">
    <h1><?php echo $text_invoice; ?> #<?php echo $order['order_id']; ?></h1>
    <table class="table table-bordered">
      <thead>
        <tr>
          <td colspan="2"><?php echo $text_order_detail; ?></td>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td style="width: 50%;">
            <address>
            <strong><?php echo $order['store_name']; ?></strong><br />
            <?php echo $order['store_address']; ?>
            </address>
            <b><?php echo $text_email; ?></b> <?php echo $order['store_email']; ?><br />
            <b><?php echo $text_website; ?></b> <a href="<?php echo $order['store_url']; ?>"><?php echo $order['store_url']; ?></a></td>
          <td style="width: 50%;"><b><?php echo $text_date_added; ?></b> <?php echo $order['date_added']; ?><br />
            <?php if ($order['invoice_no']) { ?>
            <b><?php echo $text_invoice_no; ?></b> <?php echo $order['invoice_no']; ?><br />
            <?php } ?>
            <b><?php echo $text_order_id; ?></b> <?php echo $order['order_id']; ?><br />
            <b><?php echo $text_payment_method; ?></b> <?php echo $order['payment_method']; ?>
          </td>
        </tr>
      </tbody>
    </table>
    <table class="table table-bordered">
      <thead>
        <tr>
          <td style="width: 50%;"><b><?php echo $text_payment_address; ?></b></td>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td><address>
            <?php echo $order['payment_address']; ?>
            </address></td>
        </tr>
      </tbody>
    </table>
    <table class="table table-bordered">
      <thead>
        <tr>
          <td><b><?php echo $column_product; ?></b></td>
          <td class="text-right"><b><?php echo $column_quantity; ?></b></td>
          <td class="text-right"><b><?php echo $column_price; ?></b></td>
          <td class="text-right"><b><?php echo $column_total; ?></b></td>
        </tr>
      </thead>
      <tbody>
        <?php foreach ($order['product'] as $product) { ?>
        <tr>
          <td><?php echo $product['name']; ?></td>
          <td class="text-right"><?php echo $product['quantity']; ?></td>
          <td class="text-right"><?php echo $product['price']; ?></td>
          <td class="text-right"><?php echo $product['total']; ?></td>
        </tr>
        <?php } ?>
        <?php foreach ($order['total'] as $total) { ?>
        <tr>
          <td class="text-right" colspan="3"><b><?php echo $total['title']; ?></b></td>
          <td class="text-right"><?php echo $total['text']; ?></td>
        </tr>
        <?php } ?>
      </tbody>
    </table>
  </div>
  <?php } ?>
</div>
</body>
</html>