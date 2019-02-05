INSERT INTO `Card`
    (`name`,
	`description`,
	`card_group_id`,
	`player_resource_set_id`,
	`player_building_set_id`,
	`player_upgrade_set_id`,
	`enemy_resource_set_id`,
	`enemy_building_set_id`,
	`enemy_upgrade_set_id`)
VALUES
    ( "buy trash", "buy youre trash", 1, 1, 1, 1,1,1,1),
    ( "buy ostanovku", "buy youre ostanovku", 2, 2, 3, 2,null,null,null),
    ( "buy  podezd", "buy youre podezd", 2, 3, 1, 4,null,null,null),
    ( "buy resource", "buy youre resourc", 3, 4, 1, 1,2,2,1),
    ( "drop enemy build", "drop build ", 4, null, null, null,2,3,4);


INSERT INTO `Card_Group` ( name, description)
VALUES
    ( 'Build', 'buy build'),
    ('Upgrade', ' upgrade build'),
    ('Trade', 'buy resource'),
    ('Take action', 'Take any');