import React from 'react';
import {Navbar, NavDropdown, MenuItem, NavItem} from 'react-bootstrap';
import {Searchbar} from './search.js';
import './navbar.css';
const NavBarImpl = ()=> (
  <div>
    <Navbar fluid collapseOnSelect inverse>
      <Navbar.Header>
        <Navbar.Brand>
          <span className="visible-md visible-lg"><span className="cbp-brand">Global Travel Assessment System</span></span>
          <span className="visible-xs visible-sm"><span className="cbp-brand">GTAS</span></span>
          <span className="visible-md visible-lg"><span className="dhs-brand">U.S. Department of Homeland Security</span></span>
        </Navbar.Brand>
        <Navbar.Toggle />
      </Navbar.Header>
      <Navbar.Collapse>
        <ul className="nav navbar-nav navbar-right">
          <NavItem eventKey={1} href="https://github.com/US-CBP/gtas/issues">
            Feedback
          </NavItem>
          <NavDropdown eventKey={2} title="First Last Name" id="user-dropdown">
            <MenuItem eventKey={2.1}>Preferences</MenuItem>
            <MenuItem divider />
            <MenuItem eventKey={2.2}>Logout</MenuItem>
          </NavDropdown>
        </ul>
      </Navbar.Collapse>
    </Navbar>
    <Navbar fluid collapseOnSelect>
      <Navbar.Header>
        <Navbar.Toggle />
      </Navbar.Header>
      <Navbar.Collapse>
        <div className="nav navbar-nav navbar-form navbar-right">
          <Searchbar />
        </div>
      </Navbar.Collapse>
    </Navbar>
</div>
);

export default NavBarImpl;
