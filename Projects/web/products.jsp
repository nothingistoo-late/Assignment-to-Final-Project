<%@page import="Invoice.InvoiceDTO"%>
<%@page import="Invoice.InvoiceDAO"%>
<%@page import="Cart.CartDAO"%>
<%@page import="Cart.CartDTO"%>
<%@page import="java.util.Random"%>
<%@page import="Users.UserDTO"%>
<%@page import="Products.ProductsDTO"%>
<%@page import="java.util.List"%>
<%@page import="Products.ProductsDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <!-- Basic -->
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <!-- Mobile Metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <!-- Site Metas -->
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <link rel="shortcut icon" href="images/favicon1.png" type="">

        <title> OFFICE SUPPLY SHOP </title>

        <!-- bootstrap core css -->
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />

        <!--owl slider stylesheet -->
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css" />
        <!-- nice select  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-nice-select/1.1.0/css/nice-select.min.css" integrity="sha512-CruCP+TD3yXzlvvijET8wV5WxxEh5H8P4cmz0RFbKK6FlZ2sYl3AEsKlLPHbniXKSrDdFewhbmBK5skbdsASbQ==" crossorigin="anonymous" />
        <!-- font awesome style -->
        <link href="css/font-awesome.min.css" rel="stylesheet" />

        <!-- Custom styles for this template -->
        <link href="css/style.css" rel="stylesheet" />
        <!-- responsive style -->
        <link href="css/responsive.css" rel="stylesheet" />

    </head>

    <body class="sub_page">
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");

        %>

        <div class="hero_area">
            <!-- header section strats -->
            <header class="header_section" style="background-color: #000000">
                <div class="container">
                    <nav class="navbar navbar-expand-lg custom_nav-container ">
                        <a class="navbar-brand" href="home.jsp">
                            <span>
                                OFFICE SUPPLY SHOP
                            </span>
                        </a>

                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span class=""> </span>
                        </button>

                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav  mx-auto ">
                                <li class="nav-item active">
                                    <a class="nav-link" href="home.jsp">Home<span class="sr-only">(current)</span></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="products.jsp">Products</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="createProducts.jsp">Product</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="user.jsp">User</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="invoice.jsp">Invoice</a>
                                </li>
                            </ul>
                            <div class="user_option">
                                <form action="MainController" method="POST">
                                    <div>
                                        <% if (loginUser == null) { %>
                                        <a href="login.jsp" class="order_online">
                                            Login
                                        </a>
                                        <form class="form-inline">
                                            <button class="btn  my-2 my-sm-0 nav_search-btn" type="submit">
                                            </button>
                                        </form>
                                        <a href="login.jsp" class="order_online">
                                            Register
                                        </a>
                                        <% } else { %>
                                        <form class="form-inline">
                                            <button class="btn  my-2 my-sm-0 nav_search-btn" type="submit">
                                                <i class="fa fa-user" aria-hidden="true"></i>
                                            </button>
                                        </form>
                                        <a href="LogoutController" class="order_online">
                                            Logout
                                        </a>

                                        <a href="cart.jsp" class="order_online">
                                            Cart
                                        </a>
                                        <% }%>
                                    </div>

                            </div>
                        </div>
                    </nav>
                </div>
            </header>
            <!-- end header section -->
        </div>

        <!-- food section -->

        <section class="food_section layout_padding">  
            <div class="container">

                <div class="row mb-5">
                    <%--show case--%>    
                    <%
                        ProductsDAO dao = new ProductsDAO();
                        List<ProductsDTO> productsList = null;
                        if (request.getAttribute("Products_List") != null) {
                            productsList = (List<ProductsDTO>) request.getAttribute("Products_List");
                        } else {
                            productsList = dao.getAllProducts();
                        }
                    %>
                    <div class="col-md-9 order-2">
                        <div class="row">
                            <div class="col-md-12 mb-5">
                                <div class="float-md-left mb-4"><h2 class="text-black h5">Shop All</h2></div>
                                <%
                                    String message = (String) request.getAttribute("message");
                                    if (message == null) {
                                        message = "";
                                    }
                                %>
                                <div class="d-flex justify-content-center">
                                    <h3 style="color: red;"><%= message%></h3>
                                </div> 
                            </div>
                        </div>
                        <div class="row mb-5">
                            <% for (ProductsDTO products : productsList) {%>
                            <div class="col-sm-6 col-lg-4 mb-4 <%= products.getType()%> grid-item" data-aos="fade-up">
                                <div class="block-4 text-center border">
                                    <figure class="block-4-image">
                                        <img src="picture/<%= products.getImage()%>" alt="<%= products.getName()%>" class="img-fluid">
                                    </figure>
                                    <form action="MainController" method="POST">
                                        <div class="block-4-text p-4">
                                            <h3><input type="text" name="name" value="<%= products.getName()%>" class="form-control" /></h3>
                                            <p class="mb-0"><input type="text" name="description" value="<%= products.getDescription()%>" class="form-control" /></p>
                                            <p class="text-primary font-weight-bold">$<input type="text" name="price" value="<%= products.getPrice()%>" class="form-control" /></p>
                                            <p>Type: <input type="text" name="type" value="<%= products.getType()%>" class="form-control" /></p>
                                            <input type="hidden" name="image" value="<%= products.getImage()%>">
                                            <input type="hidden" name="idProducts" value="<%= products.getIdProducts()%>">
                                            <button type="submit" name="action" value="Delete" class="btn btn-primary">Delete</button>
                                            <button type="submit" name="action" value="UpdateProducts" class="btn btn-primary">Update</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <% }%>
                        </div>
                    </div>
                </div>
            </div>
        </section>



        <!-- end food section -->

        <!-- footer section -->
        <footer class="footer_section">
            <div class="container">
                <div class="row">
                    <div class="col-md-4 footer-col">
                        <div class="footer_contact">
                            <h4>
                                Contact Us
                            </h4>
                            <div class="contact_link_box">
                                <a href="">
                                    <i class="fa fa-map-marker" aria-hidden="true"></i>
                                    <span>
                                        Ho Chi Minh City
                                    </span>
                                </a>
                                <a href="">
                                    <i class="fa fa-phone" aria-hidden="true"></i>
                                    <span>
                                        Call +84 69 419 222
                                    </span>
                                </a>
                                <a href="">
                                    <i class="fa fa-envelope" aria-hidden="true"></i>
                                    <span>
                                        namvnse181565@fpt.edu.vn
                                    </span>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 footer-col">
                        <div class="footer_detail">
                            <a href="" class="footer-logo">
                                Join the OFFICE SUPPLY SHOP Community
                            </a>
                            <p>
                                Become a part of the OFFICE SUPPLY SHOP community! Connect with fellow office supply enthusiasts, share your experiences, ask questions, and learn from others who share your passion. Our community is here to support you every step of the way.
                            </p>
                            <div class="footer_social">
                                <a href="">
                                    <i class="fa fa-facebook" aria-hidden="true"></i>
                                </a>
                                <a href="">
                                    <i class="fa fa-twitter" aria-hidden="true"></i>
                                </a>
                                <a href="">
                                    <i class="fa fa-linkedin" aria-hidden="true"></i>
                                </a>
                                <a href="">
                                    <i class="fa fa-instagram" aria-hidden="true"></i>
                                </a>
                                <a href="">
                                    <i class="fa fa-pinterest" aria-hidden="true"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 footer-col">
                        <h4>
                            Opening Hours
                        </h4>
                        <p>
                            Everyday
                        </p>
                        <p>
                            08.00 Am -10.00 Pm
                        </p>
                    </div>
                </div>
                <div class="footer-info">
                    <p>
                        &copy; <span id="displayYear"></span> All Rights Reserved By
                        <a href="https://html.design/">Free Html Templates</a><br><br>
                        &copy; <span id="displayYear"></span> Distributed By
                        <a href="https://themewagon.com/" target="_blank">ThemeWagon</a>
                    </p>
                </div>
            </div>
        </footer>
        <!-- footer section -->

        <!-- jQery -->
        <script src="js/jquery-3.4.1.min.js"></script>
        <!-- popper js -->
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous">
        </script>
        <!-- bootstrap js -->
        <script src="js/bootstrap.js"></script>
        <!-- owl slider -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js">
        </script>
        <!-- isotope js -->
        <script src="https://unpkg.com/isotope-layout@3.0.4/dist/isotope.pkgd.min.js"></script>
        <!-- nice select -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-nice-select/1.1.0/js/jquery.nice-select.min.js"></script>
        <!-- custom js -->
        <script src="js/custom.js"></script>
        <!-- Google Map -->
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCh39n5U-4IoWpsVGUHWdqB6puEkhRLdmI&callback=myMap">
        </script>
        <!-- End Google Map -->


    </body>

</html>