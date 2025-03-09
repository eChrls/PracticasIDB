<!DOCTYPE html>
<html lang="en">
    <head>  
        <title>Audizz - Contacto </title>
        <meta charset="UTF-8">
        <link href="Estilos/estilosContact.css" rel="stylesheet">
        <link href="Images/favicon.png" rel="shortcut icon">
    </head>
    <body>
        <header>
            <a href="login.html">
            <h5>Subscription service R7/day</h5>
            </a>
        <nav>
            
            <img src="Images/logo.png" class="logo">
            <div><a href="rock.php" class="enlace" hidden id="rock">Rock</a></div>
            <div><a href="login.php" class="enlace" hidden>Login</a></div>
            <div><a href="contact.php" class="enlace" hidden>Contacto</a></div>
           
        </nav>
    </header>
       

        <p>Contact</p>
        <div class="form-style">
            
        <form action="http://localhost:8080/webapi/addComment" method="post">
            
            <label>Your Name:</label>
            <br><br>
            <textarea rows="1" cols="62" value="name" placeholder="Your Name" ></textarea>
            <br><br>
            <label>Your Email:</label>
            <br>
            <br>
            <textarea rows="1" cols="62" value="email" placeholder="Your Email"></textarea>
            <br><br>
            <label>Your phone:</label>
            <br><br>
            <textarea rows="1" cols="62" value="phone" placeholder="Your phone"></textarea>
            <br><br>
            <label>Message:</label>
            <br><br>
            <textarea rows="10" cols="62" value="texto"></textarea>
            <br>
            <input type="button" name="button" value="SEND" class="btn btn-primary">
        </form>
        </div>
        
        <footer>
            <p>
                This is a subscription service with premium features such as applications, emotions, wallpapers, videos, images, animated images. To take advantage of this service you need to be 18+ and have the bill payers permission. Service is available to users from South Africa only. If you have any queries while using this service or prior to subscribing, please contact our support email:info@audizz.fun. To exit this service, dial *135*997#. Support line: 0105970848. Tariff R7/day. General Terms and Conditions. Extra network charges may apply. ZALTON. WASPA member: 1619
            <br><br>
                © 2025Copyright Audizz. All Rights Reserved
            </p>
        </footer>
    </body>
</html>