<!-- Text --><div class="panel panel-default">    <div class="panel-heading">        <h3 class="panel-title"><?=$featured_name?></h3>    </div>    <div class="panel-body" style="padding-bottom: 0;">         <div class="row products products-featured">            <?php foreach ($products as $product) { ?>            <div class="modal fade" tabindex="-1" role="dialog" id="product-featured-<?=$product['id']?>">                <div class="modal-dialog">                    <div class="modal-content">                        <div class="modal-header">                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>                            <h4 class="modal-title"><?=$product['name']?></h4>                        </div>                        <div class="modal-body">                            <p><?=$product['long_description']?></p>                        </div>                        <div class="modal-footer">                            <button type="button" class="btn btn-default" data-dismiss="modal"><?=$button_close?></button>                            <a href="<?=$product['href']?>" class="btn btn-dark" role="button"><i class="fa fa-shopping-basket"></i> <?=$product['price']?></a>                        </div>                    </div><!-- /.modal-content -->                </div><!-- /.modal-dialog -->            </div><!-- /.modal -->            <div class="col-sm-12 col-md-12">                <div class="thumbnail">                    <div class="img-area" style="background-image: url(dashboard/view/image/<?=$product['thumb']?>);"></div>                    <div class="caption">                        <h3><?=$product['name']?></h3>                        <p>                            <a href="#" class="btn btn-dark" role="button" data-toggle="modal" data-target="#product-featured-<?=$product['id']?>"><i class="fa fa-plus"></i> <?=$button_more?></a>                            <a href="<?=$product['href']?>" class="btn btn-default" role="button"><i class="fa fa-shopping-basket"></i> COMPRAR: <?=$product['price_special']?></a>                        </p>                    </div>                 </div>            </div>            <?php } ?>            <?php if (empty($products)) { ?>            <div class="col-sm-12 col-md-12">                <div class="alert alert-default" role="alert"><i class="fa fa-times-circle"></i> text_not_found</div>            </div>            <?php } ?>        </div>    </div></div>