package com.epam.cdp.jndi.dao.user.settings;

import java.util.List;

import com.epam.cdp.jndi.dao.BaseDAO;
import com.epam.cdp.jndi.model.User;
import com.epam.cdp.jndi.model.UserSettings;

public interface UserSettingDAO extends BaseDAO<UserSettings, Long> {

	List<UserSettings> getAllByUser(User user);

}
