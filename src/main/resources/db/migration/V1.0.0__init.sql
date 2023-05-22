CREATE OR REPLACE FUNCTION public.searchStudent(
	s_name_classe character varying,
	s_lastName_teacher character varying,
	s_firstName_teacher character varying
    )
-- 	RETURNS SETOF public.student
	RETURNS TABLE (id bigint,last_name character varying,first_name character varying,classe_id bigint)
	LANGUAGE 'plpgsql'
	COST 100
	VOLATILE PARALLEL UNSAFE
	ROWS 1000
AS $BODY$
begin
return query
select st.id,st.last_name,st.first_name,st.classe_id from public.student as st
                                                    inner join classe cl on st.classe_id=cl.id
                                                    inner join teacher t on cl.teacher_id=t.id
where (LOWER(cl.name) LIKE  '%' || LOWER(s_name_classe) || '%' or s_name_classe='') and
    (LOWER(t.last_name) LIKE  '%' || LOWER(s_lastName_teacher) || '%' or s_lastName_teacher='') and
    (LOWER(t.first_name) LIKE  '%' || LOWER(s_firstName_teacher) || '%' or s_firstName_teacher='') ;
end;
$BODY$;
