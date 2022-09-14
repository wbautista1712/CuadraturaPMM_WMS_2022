package com.cuadratura.app.mysql.repository.impl;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.mysql.repository.AjustePmmWmsRepositoryCustom;


@Repository
@Transactional
public class AjustePmmWmsRepositoryImpl implements AjustePmmWmsRepositoryCustom{
	private static final Logger LOGGER = LogManager.getLogger(AjustePmmWmsRepositoryImpl.class);
}
