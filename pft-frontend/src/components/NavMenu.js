import React from "react";
import { Link } from "react-router-dom";

function NavMenu(props) {
    let { loggedIn, setLoggedIn } = props;
    return (
        <nav className="nav-menu">
            <ul className="flex gap-10 justify-center">
                <li>
                    <Link to="/">Home</Link>
                </li>
                {loggedIn && (<>
                    <li>
                    <Link to="/">Accounts</Link>
                    </li>
                    <li>
                    <Link to="/">Transactions</Link>
                    </li></>
                )}
                {!loggedIn && (<>
                    <li>
                    <Link to="/login">Log In</Link>
                    </li>
                    <li>
                    <Link to="/signup">Sign Up</Link>
                    </li>
                </>
                )}

                <li>
                    <Link to="/about">About</Link>
                </li>
            </ul>
        </nav>
    );
}

export default NavMenu;