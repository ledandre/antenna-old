ALTER TABLE videoLists DROP FOREIGN KEY FK_videoLists_video
ALTER TABLE schedules DROP FOREIGN KEY FK_schedules_channel
ALTER TABLE machines DROP FOREIGN KEY FK_machines_channel
ALTER TABLE commands DROP FOREIGN KEY FK_commands_machine
ALTER TABLE schedules_videoLists DROP FOREIGN KEY FK_schedules_videoLists_videoList_id
ALTER TABLE schedules_videoLists DROP FOREIGN KEY FK_schedules_videoLists_Schedule_id
DROP TABLE channels
DROP TABLE videos
DROP TABLE videoLists
DROP TABLE schedules
DROP TABLE machines
DROP TABLE users
DROP TABLE commands
DROP TABLE schedules_videoLists
