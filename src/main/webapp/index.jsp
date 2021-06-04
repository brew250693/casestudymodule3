<%--
  Created by IntelliJ IDEA.
  User: Brew
  Date: 6/1/2021
  Time: 10:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Book Home</title>
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <meta content="eCommerce HTML Template Free Download" name="keywords">
  <meta content="eCommerce HTML Template Free Download" name="description">

  <!-- Favicon -->
  <link href="img/favicon.ico" rel="icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400|Source+Code+Pro:700,900&display=swap" rel="stylesheet">

  <!-- CSS Libraries -->
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
  <link href="lib/slick/slick.css" rel="stylesheet">
  <link href="lib/slick/slick-theme.css" rel="stylesheet">

  <!-- Template Stylesheet -->
  <link href="css/style.css" rel="stylesheet">
</head>

<body>
<!-- Top bar Start -->
<div class="top-bar">
  <div class="container-fluid">
    <div class="row">
      <div class="col-sm-6">
        <i class="fa fa-envelope"></i>
        support@email.com
      </div>
      <div class="col-sm-6">
        <i class="fa fa-phone-alt"></i>
        +84-989-665336
      </div>
    </div>
  </div>
</div>
<!-- Top bar End -->

<!-- Nav Bar Start -->
<div class="nav">
  <div class="container-fluid">
    <nav class="navbar navbar-expand-md bg-dark navbar-dark">
      <a href="#" class="navbar-brand">MENU</a>
      <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
        <div class="navbar-nav mr-auto">
          <a href="/" class="nav-item nav-link active">Home</a>
          <a href="/book" class="nav-item nav-link">Book Manager</a>
          <a href="/book?action=create" class="nav-item nav-link">Book Add</a>
          <a href="/book?action=statusbook" class="nav-item nav-link">Book Status</a>
          <a href="/location" class="nav-item nav-link">Location Manager</a>
          <a href="/location?action=create" class="nav-item nav-link">Location Add</a>
        </div>
        <div class="navbar-nav ml-auto">
          <div class="nav-item dropdown">
            <a href="/login.html" class="nav-item nav-link">Login & Register</a>
          </div>
        </div>
      </div>
    </nav>
  </div>
</div>
<!-- Nav Bar End -->

<!-- Bottom Bar Start -->
<div class="bottom-bar">
  <div class="container-fluid">
    <div class="row align-items-center">
      <div class="col-md-3">
        <div class="logo">
          <a href="/">
            <img src="img/logo.jpg" alt="Logo">
          </a>
        </div>
      </div>
      <div class="col-md-6">
        <div class="search">
          <input type="text" placeholder="Search">
          <button><i class="fa fa-search"></i></button>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- Bottom Bar End -->

<!-- Main Slider Start -->
<div class="header">
  <div class="container-fluid">
    <div class="row">
      <div class="col-md-3">
        <nav class="navbar bg-light">
          <ul class="navbar-nav">
            <li class="nav-item">
              <a class="nav-link" href="/"><i class="fa fa-home"></i>HOME</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#"><i class="fa fa-shopping-bag"></i>SÁCH VĂN HỌC</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#"><i class="fa fa-plus-square"></i>SÁCH KỸ NĂNG</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#"><i class="fa fa-female"></i>SÁCH THIẾU NHI</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#"><i class="fa fa-child"></i>SÁCH THAM KHẢO</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#"><i class="fa fa-tshirt"></i>TRUYỆN TRANH</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#"><i class="fa fa-mobile-alt"></i>GIỚI THIỆU</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#"><i class="fa fa-microchip"></i>LIÊN HỆ</a>
            </li>
          </ul>
        </nav>
      </div>
      <div class="col-md-6">
        <div class="header-slider normal-slider">
          <div class="header-slider-item">
            <img src="img/slider-1.png" alt="Slider Image" />
          </div>
          <div class="header-slider-item">
            <img src="img/slider-2.png" alt="Slider Image" />
          </div>
          <div class="header-slider-item">
            <img src="img/slider-3.png" alt="Slider Image" />
          </div>
        </div>
      </div>
      <div class="col-md-3">
        <div class="header-img">
          <div class="img-item">
            <img src="img/category-1.jpg" />
          </div>
          <div class="img-item">
            <img src="img/category-2.jpg" />
          </div>
          <div class="img-item">
            <img src="img/category-3.jpg" />
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- Main Slider End -->

<!-- Brand Start -->
<div class="brand">
  <div class="container-fluid">
    <div class="brand-slider">
      <div class="brand-item"><img src="img/brand-1.png" alt=""></div>
      <div class="brand-item"><img src="img/brand-2.png" alt=""></div>
      <div class="brand-item"><img src="img/brand-3.png" alt=""></div>
      <div class="brand-item"><img src="img/brand-4.png" alt=""></div>
      <div class="brand-item"><img src="img/brand-5.png" alt=""></div>
      <div class="brand-item"><img src="img/brand-6.png" alt=""></div>
    </div>
  </div>
</div>
<!-- Brand End -->

<!-- Call to Action Start -->
<div class="call-to-action">
  <div class="container-fluid">
    <div class="row align-items-center">
      <div class="col-md-6">
        <h1>Call us for any queries</h1>
      </div>
      <div class="col-md-6">
        <a href="tel:0123456789">+84-989665336</a>
      </div>
    </div>
  </div>
</div>
<!-- Call to Action End -->

<!-- Newsletter Start -->
<div class="newsletter">
  <div class="container-fluid">
    <div class="row">
      <div class="col-md-6">
        <h1>Subscribe Our Newsletter</h1>
      </div>
      <div class="col-md-6">
        <div class="form">
          <input type="email" value="Your email here">
          <button>Submit</button>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- Newsletter End -->

<!-- Review Start -->
<div class="review">
  <div class="container-fluid">
    <div class="row align-items-center review-slider normal-slider">
      <div class="col-md-6">
        <div class="review-slider-item">
          <div class="review-img">
            <img src="img/review-1.jpg" alt="Image">
          </div>
          <div class="review-text">
            <h2>Khánh Kháu Khỉnh</h2>
            <h3>Profession</h3>
            <div class="ratting">
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
            </div>
            <p>
              Phòng sách quá tuyệt vời!
            </p>
          </div>
        </div>
      </div>
      <div class="col-md-6">
        <div class="review-slider-item">
          <div class="review-img">
            <img src="img/review-2.jpg" alt="Image">
          </div>
          <div class="review-text">
            <h2>Phương Phúng Phính</h2>
            <h3>Profession</h3>
            <div class="ratting">
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
            </div>
            <p>
              Phòng sách quá chất lượng!
            </p>
          </div>
        </div>
      </div>
      <div class="col-md-6">
        <div class="review-slider-item">
          <div class="review-img">
            <img src="img/review-3.jpg" alt="Image">
          </div>
          <div class="review-text">
            <h2>Bella</h2>
            <h3>Profession</h3>
            <div class="ratting">
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
              <i class="fa fa-star"></i>
            </div>
            <p>
              Phòng sách quá cute!
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- Review End -->

<!-- Footer Start -->
<div class="footer">
  <div class="container-fluid">
    <div class="row">
      <div class="col-lg-3 col-md-6">
        <div class="footer-widget">
          <h2>Get in Touch</h2>
          <div class="contact-info">
            <p><i class="fa fa-map-marker"></i>Codegym-HaNoi VietNam</p>
            <p><i class="fa fa-envelope"></i>brew250693@gmail.com</p>
            <p><i class="fa fa-phone"></i>+84 989665336</p>
          </div>
        </div>
      </div>

      <div class="col-lg-3 col-md-6">
        <div class="footer-widget">
          <h2>Follow Us</h2>
          <div class="contact-info">
            <div class="social">
              <a href=""><i class="fab fa-twitter"></i></a>
              <a href=""><i class="fab fa-facebook-f"></i></a>
              <a href=""><i class="fab fa-linkedin-in"></i></a>
              <a href=""><i class="fab fa-instagram"></i></a>
              <a href=""><i class="fab fa-youtube"></i></a>
            </div>
          </div>
        </div>
      </div>

      <div class="col-lg-3 col-md-6">
        <div class="footer-widget">
          <h2>Company Info</h2>
          <ul>
            <li><a href="#">About Us</a></li>
            <li><a href="#">Privacy Policy</a></li>
            <li><a href="#">Terms & Condition</a></li>
          </ul>
        </div>
      </div>

      <div class="col-lg-3 col-md-6">
        <div class="footer-widget">
          <h2>Purchase Info</h2>
          <ul>
            <li><a href="#">Pyament Policy</a></li>
            <li><a href="#">Shipping Policy</a></li>
            <li><a href="#">Return Policy</a></li>
          </ul>
        </div>
      </div>
    </div>

    <div class="row payment align-items-center">
      <div class="col-md-6">
        <div class="payment-method">
          <h2>We Accept:</h2>
          <img src="img/payment-method.png" alt="Payment Method" />
        </div>
      </div>
      <div class="col-md-6">
        <div class="payment-security">
          <h2>Secured By:</h2>
          <img src="img/godaddy.svg" alt="Payment Security" />
          <img src="img/norton.svg" alt="Payment Security" />
          <img src="img/ssl.svg" alt="Payment Security" />
        </div>
      </div>
    </div>
  </div>
</div>
<!-- Footer End -->

<!-- Footer Bottom Start -->
<div class="footer-bottom">
  <div class="container">
    <div class="row">
      <div class="col-md-6 copyright">
        <p>Copyright &copy; <a href="https://htmlcodex.com">HTML Codex</a>. All Rights Reserved</p>
      </div>

      <div class="col-md-6 template-by">
        <p>Template By <a href="https://htmlcodex.com">HTML Codex</a></p>
      </div>
    </div>
  </div>
</div>
<!-- Footer Bottom End -->

<!-- Back to Top -->
<a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>

<!-- JavaScript Libraries -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
<script src="lib/easing/easing.min.js"></script>
<script src="lib/slick/slick.min.js"></script>

<!-- Template Javascript -->
<script src="js/main.js"></script>
</body>
</html>

