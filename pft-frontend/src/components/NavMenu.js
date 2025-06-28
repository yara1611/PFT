import React from "react";
import { Link } from "react-router-dom";

function NavMenu(props) {
    let { loggedIn, setLoggedIn } = props;
    console.log("NavMenu props", props);
    return (
        <nav className="nav-menu">
            <ul className="flex gap-10 justify-center">
                <li>
                    <Link to="/">Dashboard</Link>
                </li>
                {loggedIn && (<>
                    <li>
                    <Link to="/accounts">Accounts</Link>
                    </li>
                    <li>
                    <Link to="/records">Records</Link>
                    </li>
                    <li>
                    <Link to="/">Imports</Link>
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
            </ul>
        </nav>
    );
}

export default NavMenu;