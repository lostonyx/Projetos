<?php
function send_mail($recipient, $subject, $message, $inreply = null, $copy = false){

    require_once("phpmailer/class.phpmailer.php");

    $mail = new PHPMailer();
    $body = $message;
    $mail->IsSMTP();
    $mail->FromName = "BRCAST";
    $mail->From = 'nao-responda@brcast.com.br';
    if ($inreply != null){
        $mail->AddReplyTo($inreply);
        if ($copy == true){
            $mail->AddBCC($inreply);
        }
    }
    $mail->Subject = $subject;
    $mail->AltBody = strip_tags($message);
    $mail->MsgHTML($message);
    $mail->AddAddress($recipient);
    $mail->SMTPAuth = true;
    $mail->Host = 'mail.brcast.com.br';
    $mail->Username = 'autentica@brcast.com.br';
    $mail->Password = '598475';
    $mail->Port = '25';
    if ($mail->Send()){
        return true;
    }
    else{
        return false;
    }

}
?>
