 <Script>
    function validar()
        {
            var form = document.forms["formlogin"]
            var login= form.user.value
            var senha= form.password.value
            
            if(login =="" || login=null)
            {
                alert("O Campo usuário não foi preenchido")
                login.focus;
                return false;
            }
            if(senha =="" ||senha=null)
            {
            alert("O Campo senha não foi preenchido")
            senha.focus;
            return false;
            }
           
        }            
            
        
    </Script>