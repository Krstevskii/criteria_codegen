package com.ikrstevs.criteriacodegenapiimpl.criteria;

import com.ikrstevs.criteriacodegenapi.filter.*;
import lombok.Data;
@Data
public class BookCriteria{

private LocalDateFilter date;
private StringFilter author;
private LongFilter id;
}
