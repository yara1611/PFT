import React from "react";

function NavMenu(props) {
    let { loggedIn, setLoggedIn } = props;
    return (
        <nav className="nav-menu">
            <ul className="flex space-x-4">
                <li>
                    <a href="#home">Home</a>
                </li>
                {loggedIn && (<>
                    <li>
                        <a href="#about">Accounts</a>
                    </li>
                    <li>
                        <a href="#services">Transactions</a>
                    </li></>
                )}
                {!loggedIn && (<>
                    <li>
                        <a href="#about">Log In</a>
                    </li>
                    <li>
                        <a href="#services">Sign Up</a>
                    </li></>
                )}

                <li>
                    <a href="#contact">Contact</a>
                </li>
            </ul>
        </nav>
    );
}

export default NavMenu;