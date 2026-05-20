create table if not exists users (
    id serial primary key,
    username varchar(100) unique not null,
    password_hash varchar(255) not null,
    role varchar(50) not null default 'user'
);

create table if not exists prizes (
    id serial primary key,
    award_year integer not null,
    category varchar(100) not null,
    category_title varchar(255) not null,
    top_motivation text,
    detail_link text,
    updated_at timestamp not null,
    unique(award_year, category)
);

create table if not exists laureates (
    id serial primary key,
    external_id varchar(100),
    prize_id integer not null references prizes(id) on delete cascade,
    full_name varchar(255) not null,
    portion varchar(20),
    motivation text,
    birth_country varchar(255),
    birth_place varchar(255),
    portrait_url text
);

create table if not exists user_prizes (
    user_id integer not null references users(id) on delete cascade,
    prize_id integer not null references prizes(id) on delete cascade,
    added_at timestamp not null,
    primary key(user_id, prize_id)
);
