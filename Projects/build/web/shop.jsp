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
                                    <a class="nav-link" href="home.jsp">HOME<span class="sr-only">(current)</span></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="shop.jsp">SHOP</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="about.jsp">ABOUT</a>
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
                <div class="heading_container heading_center">
                    <h2>Our Menu</h2>
                </div>
                <div class="border p-4 rounded mb-4">
                    <div class="mb-4">
                        <form action="SearchPriceController" method="POST" id="priceForm">
                            <h3 class="mb-3 h6 text-uppercase text-black d-block">Filter by Price</h3>
                            <label for="minPrice">Min Price:</label>
                            <input type="number" id="minPrice" name="minPrice" min="0" step="0.01" value="<%= request.getParameter("minPrice") != null ? request.getParameter("minPrice") : ""%>" required>
                            <label for="maxPrice">Max Price:</label>
                            <input type="number" id="maxPrice" name="maxPrice" min="0" step="0.01" value="<%= request.getParameter("maxPrice") != null ? request.getParameter("maxPrice") : ""%>" required>
                            <input type="hidden" name="action" value="SearchPrice">
                            <div class="mb-1"><input type="submit" value="Filter"></div>
                            <div class="mb-1">
                                <a href="shop.jsp" >
                                    Reset
                                </a>
                            </div>
                        </form>
                    </div>
                </div>

                <ul class="filters_menu">
                    <li class="active" data-filter="*">All</li>
                    <li data-filter=".pens">Pens</li>
                    <li data-filter=".notebooks">Notebooks</li>
                    <li data-filter=".erasers">Erasers</li>
                    <li data-filter=".rulers">Rulers</li>
                </ul>

                <% String message = (String) request.getAttribute("message"); %>
                <% if (message != null && !message.isEmpty()) {%>
                <div class="d-flex justify-content-center">
                    <h3 style="color: red;"><%= message%></h3>
                </div>
                <% } %>


                <div class="filters-content">
                    <div class="row grid">
                        <%
                            List<ProductsDTO> productsList = (List<ProductsDTO>) request.getAttribute("Products_List");
                            if (productsList == null) {
                                ProductsDAO dao = new ProductsDAO();
                                productsList = dao.getAllProducts();
                            }
                            for (ProductsDTO products : productsList) {
                                String typeClass = products.getType().toLowerCase();
                        %>
                        <div class="col-sm-6 col-lg-4 all <%= typeClass%>">
                            <div class="box">
                                <div>
                                    <div class="img-box">
                                        <img src="picture/<%= products.getImage()%>" alt="<%= products.getName()%>">
                                    </div>
                                    <div class="detail-box">
                                        <h5><%= products.getName()%></h5>
                                        <p><%= products.getDescription()%></p>
                                        <div class="options">
                                            <h6>$<%= products.getPrice()%></h6>
                                            <form action="AddToCartController" method="POST">
                                                <input type="hidden" name="idProducts" value="<%= products.getIdProducts()%>">
                                                <input type="hidden" name="price" value="<%= products.getPrice()%>">
                                                <input type="hidden" name="image" value="<%= products.getImage()%>">
                                                <input type="hidden" name="userId" value="<%= loginUser != null ? loginUser.getUserId() : ""%>">
                                                <input type="hidden" name="invId" value="null">
                                                <input type="hidden" name="quantity" value="1">
                                                <button type="submit" name="action" value="Add" class="btn btn-primary">Add to Cart</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <% }%>
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