import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';
import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown
    icon="th-list"
    name={translate('global.menu.entities.main')}
    id="entity-menu"
    style={{ maxHeight: '80vh', overflow: 'auto' }}
  >
    <MenuItem icon="asterisk" to="/tos">
      <Translate contentKey="global.menu.entities.tos" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/avatar">
      <Translate contentKey="global.menu.entities.avatar" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/issue">
      <Translate contentKey="global.menu.entities.issue" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/issue-option">
      <Translate contentKey="global.menu.entities.issueOption" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/issue-resource">
      <Translate contentKey="global.menu.entities.issueResource" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/category-2-avatar">
      <Translate contentKey="global.menu.entities.category2Avatar" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/interact">
      <Translate contentKey="global.menu.entities.interact" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/emotion">
      <Translate contentKey="global.menu.entities.emotion" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/repute">
      <Translate contentKey="global.menu.entities.repute" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/regular">
      <Translate contentKey="global.menu.entities.regular" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/report">
      <Translate contentKey="global.menu.entities.report" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/chemistry">
      <Translate contentKey="global.menu.entities.chemistry" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/category-2-issue">
      <Translate contentKey="global.menu.entities.category2Issue" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/pack">
      <Translate contentKey="global.menu.entities.pack" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/deal">
      <Translate contentKey="global.menu.entities.deal" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/deal-option">
      <Translate contentKey="global.menu.entities.dealOption" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
