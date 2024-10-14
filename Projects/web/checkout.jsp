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

        <title>OFFICE SUPPLY SHOP</title>

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
                                        <% } %>
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
        <%
            InvoiceDTO invoice = (InvoiceDTO) session.getAttribute("invoice");
            
        %>
        <form action="CreateInvoiceController" method="POST">
        <div class="row">
          
          <div class="col-md-6 mb-5 mb-md-0">
            <h2 class="h3 mb-3 text-black">Bill Details</h2>
            <div class="p-3 p-lg-5 border">
                <div class="form-group row">
                    <div class="col-md-12">
                        <label for="c_invId" class="text-black">Invoice ID</label>
                        <input type="text" class="form-control" id="c_invId" name="invId" value="<%=invoice.getInvId()%>" readonly>
                    </div>
                </div>

                <div class="form-group row">
                    <div class="col-md-12">
                        <label for="c_userID" class="text-black">User ID</label>
                        <input type="text" class="form-control" id="c_userID" name="userID" value="<%=loginUser.getUserId()%>" readonly>
                    </div>
                </div>

                <div class="form-group row">
                    <div class="col-md-12">
                        <label for="c_dateBuy" class="text-black">Date of Purchase<span class="text-danger">*</span></label>
                        <input type="datetime-local" class="form-control" id="c_dateBuy" name="dateBuy">
                    </div>
                </div>

                <div class="form-group row">
                    <div class="col-md-12">
                        <label for="c_gmail" class="text-black">Email<span class="text-danger">*</span></label>
                        <input type="text" class="form-control" id="c_gmail" name="gmail"value="<%=loginUser.getGmail()%>" placeholder="Enter email">
                    </div>
                </div>

                <div class="form-group row">
                    <div class="col-md-12">
                        <label for="c_address" class="text-black">Address<span class="text-danger">*</span></label>
                        <input type="text" class="form-control" id="c_address" name="address" value="<%=loginUser.getAddress()%>"placeholder="Enter address">
                    </div>
                </div>

                <!-- Rest of your form here -->
            </div>
          </div>
                    
                    
          <div class="col-md-6">
            <div class="row mb-5">
            <div class="col-md-12">
                <h2 class="h3 mb-3 text-black">Your Order</h2>
                <div class="p-3 p-lg-5 border">
                    <table class="table site-block-order-table mb-5">
                        <thead>
                            <th>Product</th>
                            <th>Total</th>
                        </thead>
                        <tbody>
                            <% 
                            List<CartDTO> cartList = (List<CartDTO>) session.getAttribute("cartList");
                            float totalCost = 0;
                            for (CartDTO cart : cartList) { 
                                totalCost += cart.getTotalPrice();
                            %>
                                <tr>
                                    <td><%=cart.getIdProducts()%> <strong class="mx-2">x</strong> <%=cart.getQuantity()%></td>
                                    <td>$<%=cart.getTotalPrice()%></td>
                                </tr>
                            <% 
                            } 
                            %>
                            <tr>
                                <td class="text-black font-weight-bold"><strong>Order Total</strong></td>
                                <td class="text-black font-weight-bold"><strong>$<%=totalCost%></strong></td>
                            </tr>
                            
                        </tbody>
                    </table>
                    <div class="form-group">
                        <input type="hidden" name="total" value="<%=totalCost%>">
                        <button type="submit"name="action" value="CreateInvoice" class="btn btn-primary btn-lg py-3 btn-block">Place Order</button>
                      </div>
                </div>
            </div>
        </div>


          </div>
        </div>
        </form>
        <!-- </form> -->
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