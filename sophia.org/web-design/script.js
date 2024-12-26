cart = []
try {
document.querySelector("a.subscribe").addEventListener("click", e => {
  e.preventDefault();
  alert("Thank you for subscribing");
})
} catch {}

try {
document.querySelectorAll("button.add-to-cart").forEach (elem => {
  elem.addEventListener("click", e => {
    alert("Item added to the cart");
    item = new Array(...e.target.parentElement.parentElement.querySelector("img").src.matchAll(/\_(.*)\.png/g)).at (0).at (1);
    cart.push(item);
    fetch ("/api/cart/add", {method: 'POST', body: {
      item: item
    }});
    alert ("Item added to the cart");
    if (cart.length == 0)
      alert("Cart empty");
      
  // cart.push(

})
                                                                                            })
} catch {}
try {
document.querySelector("a.clear-cart").addEventListener("click", e => {
  e.preventDefault();
  if(cart.length == 0)
    alert("No items to clear.");
  else 
    alert("Cart cleared");
  cart = []
})
} catch {}

try {
document.querySelector("a.view-cart").addEventListener("click", e => {
  e.preventDefault();
  if(cart.length != 0)
    alert(cart);
  cart = []
})
} catch {}
try {
document.querySelector("a.process-order").addEventListener("click", e => {
  e.preventDefault();
  if(cart.length == 0)
    alert("Cart is empty");
  else 
    alert("Thank you for your order");
})
} catch {}
try {
document.querySelector(".contact-us form input[type=submit]").addEventListener("click", e => {
  e.preventDefault();
  alert("Thank you for your message")
});
} catch {}
