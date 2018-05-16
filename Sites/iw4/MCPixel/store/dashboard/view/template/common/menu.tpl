<ul class="main-menu">

    <li><a href="<?=$home?>"><i class="zmdi zmdi-home"></i> <?=$text_dashboard?></a></li>

    <li class="sub-menu" id="catalog"><a class="parent"><i class="fa fa-tags fa-fw"></i> <?php echo $text_catalog; ?>
        </a>
        <ul>
            <li><a href="<?php echo $product; ?>"><?php echo $text_product; ?></a></li>
            <li><a href="<?php echo $recurring; ?>"><?php echo $text_recurring; ?></a></li>
            <li><a href="<?php echo $category; ?>"><?php echo $text_category; ?></a></li>
            <li><a href="<?php echo $server; ?>"><?php echo $text_server; ?></a></li>
        </ul>
    </li>

    <li class="sub-menu" id="design"><a class="parent"><i class="fa fa-television fa-fw"></i>
            <span><?php echo $text_design; ?></span></a>
        <ul>
            <li><a href="<?php echo $layout; ?>"><?php echo $text_layout; ?></a></li>
        </ul>
    </li>

    <li class="sub-menu" id="extension"><a class="parent"><i class="fa fa-puzzle-piece fa-fw"></i>
            <span><?php echo $text_extension; ?></span></a>
        <ul>
            <li><a href="<?php echo $installer; ?>"><?php echo $text_installer; ?></a></li>
            <li><a href="<?php echo $module; ?>"><?php echo $text_module; ?></a></li>
            <li><a href="<?php echo $payment; ?>"><?php echo $text_payment; ?></a></li>
            <li><a href="<?php echo $total; ?>"><?php echo $text_total; ?></a></li>
        </ul>
    </li>

    <li class="sub-menu" id="sale"><a class="parent"><i class="fa fa-shopping-cart fa-fw"></i>
            <span><?php echo $text_sale; ?></span></a>
        <ul>
            <li><a href="<?php echo $order; ?>"><?php echo $text_order; ?></a></li>
            <li><a href="<?php echo $order_recurring; ?>"><?php echo $text_order_recurring; ?></a></li>
            <li class="sub-menu"><a class="parent"><?php echo $text_paypal ?> <i class="fa fa-caret-down"></i></a>
                <ul>
                    <li><a href="<?php echo $paypal_search ?>"><?php echo $text_paypal_search ?></a></li>
                </ul>
            </li>
        </ul>
    </li>

    <li class="sub-menu" id="customer"><a class="parent"><i class="fa fa-user fa-fw"></i>
            <span><?php echo $text_customer; ?></span></a>
        <ul>
            <li><a href="<?php echo $customer; ?>"><?php echo $text_customer; ?></a></li>
            <li><a href="<?php echo $customer_group; ?>"><?php echo $text_customer_group; ?></a></li>
            <li><a href="<?php echo $custom_field; ?>"><?php echo $text_custom_field; ?></a></li>
        </ul>
    </li>

    <li class="sub-menu"><a class="parent"><i class="fa fa-share-alt fa-fw"></i> <span><?php echo $text_marketing; ?></span></a>
        <ul>
            <li><a href="<?php echo $marketing; ?>"><?php echo $text_marketing; ?></a></li>
            <li><a href="<?php echo $contact; ?>"><?php echo $text_contact; ?></a></li>
            <li><a href="<?php echo $coupon; ?>"><?php echo $text_coupon; ?></a></li>
        </ul>
    </li>

    <li class="sub-menu" id="system"><a class="parent"><i class="fa fa-cog fa-fw"></i>
            <span><?php echo $text_system; ?></span></a>
        <ul>
            <li class="sub-menu"><a class="parent"><?php echo $text_users; ?> <i class="fa fa-caret-down"></i></a>
                <ul>
                    <li><a href="<?php echo $user; ?>"><?php echo $text_user; ?></a></li>
                    <li><a href="<?php echo $user_group; ?>"><?php echo $text_user_group; ?></a></li>
                    <li><a href="<?php echo $api; ?>"><?php echo $text_api; ?></a></li>
                </ul>
            </li>
            <li class="sub-menu"><a class="parent"><?php echo $text_localisation; ?> <i
                            class="fa fa-caret-down"></i></a>
                <ul>
                    <li><a href="<?php echo $language; ?>"><?php echo $text_language; ?></a></li>
                    <li><a href="<?php echo $currency; ?>"><?php echo $text_currency; ?></a></li>
                    <li><a href="<?php echo $order_status; ?>"><?php echo $text_order_status; ?></a></li>
                    <li><a href="<?php echo $country; ?>"><?php echo $text_country; ?></a></li>
                    <li><a href="<?php echo $zone; ?>"><?php echo $text_zone; ?></a></li>
                    <li><a href="<?php echo $geo_zone; ?>"><?php echo $text_geo_zone; ?></a></li>
                </ul>
            </li>
            <li class="sub-menu"><a class="parent"><?php echo $text_tools; ?> <i class="fa fa-caret-down"></i></a>
                <ul>
                    <li><a href="<?php echo $backup; ?>"><?php echo $text_backup; ?></a></li>
                    <li><a href="<?php echo $error_log; ?>"><?php echo $text_error_log; ?></a></li>
                </ul>
            </li>
        </ul>
    </li>

    <li class="sub-menu" id="reports"><a class="parent"><i class="fa fa-bar-chart-o fa-fw"></i>
            <span><?php echo $text_reports; ?></span></a>
        <ul>
            <li class="sub-menu"><a class="parent"><?php echo $text_sale; ?> <i class="fa fa-caret-down"></i></a>
                <ul>
                    <li><a href="<?php echo $report_sale_order; ?>"><?php echo $text_report_sale_order; ?></a></li>
                    <li><a href="<?php echo $report_sale_coupon; ?>"><?php echo $text_report_sale_coupon; ?></a></li>
                </ul>
            </li>
            <li class="sub-menu"><a class="parent"><?php echo $text_product; ?> <i class="fa fa-caret-down"></i></a>
                <ul>
                    <li>
                        <a href="<?php echo $report_product_purchased; ?>"><?php echo $text_report_product_purchased; ?></a>
                    </li>
                </ul>
            </li>
            <li class="sub-menu"><a class="parent"><?php echo $text_customer; ?> <i class="fa fa-caret-down"></i></a>
                <ul>
                    <li><a href="<?php echo $report_customer_online; ?>"><?php echo $text_report_customer_online; ?></a>
                    </li>
                    <li>
                        <a href="<?php echo $report_customer_activity; ?>"><?php echo $text_report_customer_activity; ?></a>
                    </li>
                    <li><a href="<?php echo $report_customer_order; ?>"><?php echo $text_report_customer_order; ?></a>
                    </li>
                    <li><a href="<?php echo $report_customer_credit; ?>"><?php echo $text_report_customer_credit; ?></a>
                    </li>
                </ul>
            </li>
        </ul>
    </li>

</ul>
