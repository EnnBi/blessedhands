package com.example.medico.utils;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;


public class StringToEntityConverter implements GenericConverter {
	//private static final String ID_FIELD = "id";
	private final Class<?> clazz;
	@PersistenceContext
	private EntityManager em;

	public StringToEntityConverter(Class<?> clazz) {
		super();
		System.err.println("In converter bean"+clazz);
		this.clazz = clazz;
	}

	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		Set<ConvertiblePair> types = new HashSet<GenericConverter.ConvertiblePair>();
		types.add(new ConvertiblePair(String.class, this.clazz));
		types.add(new ConvertiblePair(this.clazz, String.class));
		return types;
	}

	@Override
	public Object convert(Object source, TypeDescriptor sourceType,
			TypeDescriptor targetType) {
		System.err.println("in converter converting  " + sourceType.getType());
		if (String.class.equals(sourceType.getType())) {
			if (StringUtils.isBlank((String) source)) {
				return null;
			}
			try {
				Long id = Long.parseLong((String) source);
				return this.em.find(this.clazz, id);
			} catch (Exception e) {
				try{
				Integer id = Integer.parseInt((String) source);
				return this.em.find(this.clazz, id);
				}catch(Exception ex){
					return null;
				}
				
			}
		}/* else if (this.clazz.equals(sourceType.getType())) {
			try {
				if (source == null) {
					return "";
				} else {
					if(sourceType.getType().isInstance(new UserDetail())){
						return FieldUtils.readField(source, "userId", true);
					}
					else{
					return FieldUtils.readField(source, ID_FIELD, true);
					}
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}*/
		throw new IllegalArgumentException("Cannot convert " + source
				+ " into a suitable type!");
	}
}